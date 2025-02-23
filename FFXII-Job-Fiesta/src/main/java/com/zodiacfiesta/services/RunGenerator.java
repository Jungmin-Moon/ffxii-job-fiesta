package com.zodiacfiesta.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
			oneJobRun.setDateStarted(dateMade);
			
			setSameJobOneValues(chosenJob, oneJobRun);
			
			runRepo.save(oneJobRun);
			
			if (checkDateStarted(user, dateCheck, "OneJob")) {
				added = true;
			} else {
				added = false;
			}
			
		} else {
			
			added = false;
		}
		
		return added;
	}
	
	//generates six unique jobs and the player only uses those 6
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
			sixUniqueRun.setDateStarted(dateMade);
			
			setSixUniqueJobs(chosenJobs, sixUniqueRun);
			
			runRepo.save(sixUniqueRun);
			
			if (checkDateStarted(user, dateCheck, "SixUnique")) {
				added = true;
			} else {
				added = false;
			}
			
		} else {
			added = false;
		}
		
		return added;
	}
	
	//every character will have the same JobOne and JobTwo but the two jobs will be different
	public boolean generateTwoJobsAll(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if (checkIfActiveRun(user) == null) {
			List<Jobs> twoJobs = jobGenerator.oneJobEveryoneBoth();
			Runs twoJobRun = new Runs();
			twoJobRun.setRunType("TwoJobs");
			
			LocalDateTime dateMade = LocalDateTime.now();
			dateMade = dateMade.truncatedTo(ChronoUnit.SECONDS);
			LocalDateTime dateCheck = dateMade;
			twoJobRun.setDateStarted(dateMade);
			
			setTwoJobs(twoJobs, twoJobRun);
			
			runRepo.save(twoJobRun);
			
			if (checkDateStarted(user, dateCheck, "TwoJobs")) {
				added = true;
			} else 
				added = false;
			
		} else {
			added = false;
		}
		
		return added;
	}
	
	public boolean generateTwelveUniqueJobs(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if (checkIfActiveRun(user) == null) {
			ArrayList<ArrayList<Jobs>> twelveJobs = new ArrayList<>();
			Runs twelveUnique = new Runs();
			twelveUnique.setRunType("TwelveUnique");
			
			LocalDateTime dateMade = LocalDateTime.now();
			dateMade = dateMade.truncatedTo(ChronoUnit.SECONDS);
			LocalDateTime dateCheck = dateMade;
			twelveUnique.setDateStarted(dateMade);
			
			runRepo.save(twelveUnique);
			
			if (checkDateStarted(user, dateCheck, "TwelveUnique")) {
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
	
	//helper method to assign jobs for job one not job two, for unique jobs
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
	
	//setting the same job ones to each character
	private void setSameJobOneValues(Jobs firstJob, Runs newRun) {
		//newRun.setDateStarted(dateMade);
		
		//can not think of a better way for single job runs to set values easier
		newRun.setVaanJobOne(firstJob);
		newRun.setFranJobOne(firstJob);
		newRun.setBalthierJobone(firstJob);
		newRun.setBaschJobOne(firstJob);
		newRun.setAsheJobOne(firstJob);
		newRun.setPeneloJobOne(firstJob);
	}
	
	//setting the same job twos to each character
	private void setSameJobTwoValues(Jobs secondJob, Runs newRun) {
		newRun.setVaanJobTwo(secondJob);
		newRun.setFranJobOne(secondJob);
		newRun.setBalthierJobone(secondJob);
		newRun.setBaschJobOne(secondJob);
		newRun.setAsheJobOne(secondJob);
		newRun.setPeneloJobOne(secondJob);
	}
	
	//calls on the above two methods to correctly assign each character their job
	private void setTwoJobs(List<Jobs> twoJobList, Runs newRun) {
		setSameJobOneValues(twoJobList.get(0), newRun);
		setSameJobTwoValues(twoJobList.get(1), newRun);
	}
	
	//order is Vaan, Fran, Balthier, Basch, Ashe, Penelo
	private void setSixUniqueJobs(List<Jobs> jobList, Runs newRun) {
		//newRun.setDateStarted(dateMade);
		
		for (int i = 0; i < jobList.size(); i++) {
			setCorrectJobOneCharacter(jobList, i, newRun);
		}
	}
}
