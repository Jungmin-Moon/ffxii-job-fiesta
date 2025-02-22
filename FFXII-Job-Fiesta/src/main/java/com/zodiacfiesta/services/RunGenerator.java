package com.zodiacfiesta.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
		
		if (checkIfActiveRun(user) == null) {
			
			Jobs chosenJob = jobGenerator.oneJobEveryone();
			
			Runs oneJobRun = new Runs();
			oneJobRun.setRunType("OneJob");
			
			LocalDateTime dateMade = LocalDateTime.now();
			dateMade = dateMade.truncatedTo(ChronoUnit.SECONDS);
			
			LocalDateTime dateCheck = dateMade;
			
			setSingleJobRunValues(chosenJob, oneJobRun, dateMade);
			
			runRepo.save(oneJobRun);
			
			if (checkDateStarted(user, dateCheck, "oneJobAll")) {
				added = true;
			} else {
				added = false;
			}
			
		} else {
			
			added = false;
		}
		
		return added;
	}
	
	public boolean generateSixUniqueJobs(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if (checkIfActiveRun(user) == null) {
			
			List<Jobs> chosenJobs = jobGenerator.singleDifferentJobs();
			Runs sixUniqueRun = new Runs();
			sixUniqueRun.setRunType("SixUnique");
			
			LocalDateTime dateMade = LocalDateTime.now();
			dateMade = dateMade.truncatedTo(ChronoUnit.SECONDS);
			
			LocalDateTime dateCheck = dateMade;
			
			setSixUniqueJobs(chosenJobs, sixUniqueRun, dateMade);
			
			runRepo.save(sixUniqueRun);
			
			if (checkDateStarted(user, dateCheck, "sixUniqueJobsOnly")) {
				added = true;
			} else {
				added = false;
			}
			
		} else {
			added = false;
		}
		
		return added;
	}
	
	
	
	//------------------------------------------------------------------------------------
	
	//helper methods
	public boolean profileControllerHelper(String runType, String username) {
		boolean created = false;
		
		switch(runType) {
			case "oneJobAll" -> {created = generateOneJobForAll(username);}
			case "sixUniqueJobsOnly" -> {created = generateSixUniqueJobs(username);}
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
	
	//helper method to assign jobs for job one not job two
	private void setCorrectJobOneCharacter(List<Jobs> jobList, int index, Runs run) {
		
		switch (index) {
			case 0 -> run.setVaanJobOne(jobList.get(index));
			case 1 -> run.setFranJobOne(jobList.get(index));
			case 2 -> run.setBalthierJobone(jobList.get(index));
			case 3 -> run.setBaschJobOne(jobList.get(index));
			case 4 -> run.setAsheJobOne(jobList.get(index));
			case 5 -> run.setPeneloJobOne(jobList.get(index));
		}
		
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
	
	//order is Vaan, Fran, Balthier, Basch, Ashe, Penelo
	private void setSixUniqueJobs(List<Jobs> jobList, Runs newRun, LocalDateTime dateMade) {
		
		for (int i = 0; i < jobList.size(); i++) {
			setCorrectJobOneCharacter(jobList, i, newRun);
		}
	}
}
