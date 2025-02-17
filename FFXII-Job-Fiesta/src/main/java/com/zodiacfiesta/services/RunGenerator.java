package com.zodiacfiesta.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.Jobs;
import com.zodiacfiesta.entities.Runs;
import com.zodiacfiesta.entities.User;
import com.zodiacfiesta.repositories.RunsRepository;
import com.zodiacfiesta.repositories.UserRepository;

/*
 * Class used to be a gateway between the controller and the JobGenerator to return the runs people request
 */
@Service
public class RunGenerator {
	
	/*
	 * 1. Depending on run type call a different method
	 * 2. each run type will call the JobGenerator it needs
	 * 3. it will call the Repo to insert correctly with helper methods
	 * 4. return rowCount to see if an entry was inserted or not
	 */
	JobGenerator jobGenerator;
	RunsRepository runRepo;
	UserRepository userRepo;
	
	RunGenerator(JobGenerator jobGenerator, RunsRepository runRepo, UserRepository userRepo) {
		this.jobGenerator = jobGenerator;
		this.runRepo = runRepo;
		this.userRepo = userRepo;
	}
	
	//every character has one different job only
	//each method checks that  the latest run the user has in the table is currently going and the start date is the same as one stored
	//to make sure that the run was added correctly.
	/*
	 * .truncatedTo(ChronoUnit.SECONDS);
	 */
	public boolean generateOneJobForAll(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if(checkIfActiveRun(user) == null) {
			
			Jobs chosenJob = jobGenerator.oneJobEveryone();
			
			Runs oneJobRun = new Runs();
			
			LocalDateTime dateMade = LocalDateTime.now();
			dateMade = dateMade.truncatedTo(ChronoUnit.SECONDS);
			
			LocalDateTime dateCheck = dateMade;
			
			setSingleJobRunValues(chosenJob, oneJobRun, dateMade);
			
			runRepo.save(oneJobRun);
			
			if(checkDateStarted(user, dateCheck, "oneJobAll")) {
				added = true;
			} else {
				added = false;
			}
			
		} else {
			
			added = false;
		}
		
		return added;
	}
	
	
	
	//helper methods
	public boolean profileControllerHelper(String runType, String username) {
		boolean created = false;
		
		switch(runType) {
			case "oneJobAll" -> {created = generateOneJobForAll(username);}
			default -> {created = false;}
		}
		
		return created;
	}
	
	
	//method to check the date time of the run stored in the DB with the date time that was saved to make sure the run was added
	public boolean checkDateStarted(User user, LocalDateTime timeMade, String runType) {
		long userId = user.getId();
		
		Runs run = runRepo.checkRunAdded(userId, timeMade, runType);
		
		if (run != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	//method to check that the user doesn't already have an in progress run
	private Runs checkIfActiveRun(User user) {
		Runs checkRun = runRepo.checkIfOngoing(user.getId());
		
		return checkRun;
	}
	
	
	private void setSingleJobRunValues(Jobs singleJob, Runs newRun, LocalDateTime dateMade) {
		newRun.setDateStarted(dateMade);
		
		
		//can not think of a better way for single job runs to set values easier
		newRun.setVaanJobOne(singleJob);
		newRun.setFranJobOne(singleJob);
		newRun.setBalthierJobone(singleJob);
		newRun.setBaschJobOne(singleJob);
		newRun.setAsheJobOne(singleJob);
		newRun.setPeneloJobOne(singleJob);
		
	}
}
