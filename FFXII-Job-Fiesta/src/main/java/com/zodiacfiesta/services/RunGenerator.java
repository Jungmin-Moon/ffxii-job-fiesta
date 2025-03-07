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
	
	/*
	 * Generates one job for every character to use and it will be the only job all six characters can use
	 * 
	 * @param username used to grab the user information from the database
	 * @return 		   a boolean signifying if the run was added or not
	 */
	public boolean generateOneJobForAll(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if (checkIfActiveRun(user) == null) {
			
			Jobs chosenJob = jobGenerator.oneJobEveryone();
			Runs oneJobRun = new Runs();
			
			setRunType("OneJob", oneJobRun);
			LocalDateTime dateCheck = setDateStartedAndReturn(oneJobRun);
			
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
	
	/*
	 * Generates a run where all six characters are given a unique job and those six jobs will be the only ones used
	 * 
	 * @param username used to grab user information from the database
	 * @return		   boolean signifying if the run was added or not
	 */
	public boolean generateSixUniqueJobs(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if (checkIfActiveRun(user) == null) {
			
			List<Jobs> chosenJobs = jobGenerator.singleDifferentJobs();
			Runs sixUniqueRun = new Runs();
			
			setRunType("SixUnique", sixUniqueRun);
			LocalDateTime dateCheck = setDateStartedAndReturn(sixUniqueRun);
			
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
	
	/*
	 * Generates a run where all six characters will have the same jobs for their first job and same jobs for their second one.
	 * Results in every character having the same Job One and Job Two. JobOne(same)/JobTwo(same)
	 * 
	 * @param username used to grab user information from the database
	 * @return 		   boolean signifying if the run was added or not
	 */
	public boolean generateTwoJobsAll(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if (checkIfActiveRun(user) == null) {
			List<Jobs> twoJobs = jobGenerator.oneJobEveryoneBoth();
			Runs twoJobRun = new Runs();
			twoJobRun.setRunType("TwoJobs");
			
			setRunType("TwoJobs", twoJobRun);
			LocalDateTime dateCheck = setDateStartedAndReturn(twoJobRun);
			
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
	
	/*
	 * Generates a run where all six characters will utilize all twelve jobs available in the game.
	 * Each character will have a unique combination of Job one and Job two 
	 * 
	 * @param username used to grab user information from the database
	 * @return		   boolean signifying if the run was added or not
	 */
	public boolean generateTwelveUniqueJobs(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		
		if (checkIfActiveRun(user) == null) {
			ArrayList<ArrayList<Jobs>> twelveJobs = jobGenerator.differentBothJobs();
			Runs twelveUnique = new Runs();
			twelveUnique.setRunType("TwelveUnique");
			
			setRunType("TwelveUnique", twelveUnique);
			LocalDateTime dateCheck = setDateStartedAndReturn(twelveUnique);
			
			setTwelveUniqueJobs(twelveJobs, twelveUnique);
			
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
	
	/*
	 * Generates jobs for every character but only seven total. Every character's job one will be the same while job two will be unique
	 * 
	 * @param username used to grab user information from the database
	 * @return		   boolean signifying if the run was added or not
	 */
	public boolean generateUniqueJobTwos(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		if (checkIfActiveRun(user) == null) {
			ArrayList<ArrayList<Jobs>> sevenJobs = jobGenerator.differentJobTwo();
			Runs sevenUnique = new Runs();
			sevenUnique.setRunType("UniqueJobTwo");
			
			setRunType("UniqueJobTwo", sevenUnique);
			LocalDateTime dateCheck = setDateStartedAndReturn(sevenUnique);

			setSameJobOneValues(sevenJobs.get(0).get(0), sevenUnique);
			setJobTwos(sevenJobs, sevenUnique);
			
			runRepo.save(sevenUnique);
			
			if (checkDateStarted(user, dateCheck, "UniqueJobTwo")) {
				added = true;
			} else {
				added = false;
			}
			
		} else {
			added = false;
		}
		
		return added;
	}
	
	/*
	 * Generates seven total unique jobs where every character's job two will be the same for every character and job one will be unique
	 * 
	 * @param username used to grab user information from the database
	 * @return		   boolean signifying if the run was added or not
	 */
	public boolean generateUniqeJobOnes(String username) {
		boolean added = false;
		
		User user = userRepo.getByUsername(username);
		if (checkIfActiveRun(user) == null) {
			ArrayList<ArrayList<Jobs>> sevenJobs = jobGenerator.differentJobOne();
			Runs sevenUnique = new Runs();
			sevenUnique.setRunType("UniqueJobOne");
			
			setRunType("UniqueJobOne", sevenUnique);
			LocalDateTime dateCheck = setDateStartedAndReturn(sevenUnique);
			
			setSameJobTwoValues(sevenJobs.get(1).get(0), sevenUnique);
			setJobOnes(sevenJobs, sevenUnique);
			
			runRepo.save(sevenUnique);
			
			if (checkDateStarted(user, dateCheck, "UniqueJobOne")) {
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
	
	
	/*
	 * Method that will call the correct generator method depending on what is passed from the profile controller and return a boolean
	 * if the run was successfully created.
	 * 
	 * @param runType specifies the run type sent by the controller so the method can call the right generator
	 * @param username used to assign the correct user the desired run 
	 * @return		   a Boolean telling the controller that the run was created
	 */
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
	/*
	 * Method is run during the final steps of run generation to make sure the run was added correctly by matching the LocalDateTime 
	 * and making sure it is the right LocalDateTime for the right user along with run type
	 * 
	 * @param user the user to check
	 * @param timeMade the date and time that needs to be checked
	 * @param runType the type of run that was generated
	 * @return		  returns true or false depending on if the run was added correctly
	 */
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
	/*
	 * Method used to check if a user has a run already going
	 * 
	 * @param user the user that needs to be checked
	 * @return	   will return a Runs object if it exists null otherwise
	 */
	private Runs checkIfActiveRun(User user) {
		Runs checkRun = runRepo.checkIfOngoing(user.getId());
		
		return checkRun;
	}
	
	/*
	 * Method to assign the run type to a run
	 * 
	 * @param runType value for what kind of run the user chose
	 * @param run the Runs object to save the value to
	 */
	private void setRunType(String runType, Runs run) {
		run.setRunType(runType);
	}
	
	/*
	 * Method to generate a dateStarted for a user generated run
	 * 
	 * @param run the run to assign the dateStarted value to
	 * @return dateMade the date created for dateStarted truncated to show at most the seconds column
	 */
	private LocalDateTime setDateStartedAndReturn(Runs run) {
		LocalDateTime dateMade = LocalDateTime.now();
		dateMade = dateMade.truncatedTo(ChronoUnit.SECONDS);
		
		return dateMade;
	}
	
	//helper method to assign jobs for job one not job two, for unique jobs
	/*
	 * Method sets the right job one values to each character
	 * 
	 * @param jobList the List holding the six unique jobs
	 * @param index used to set the right job to the right character
	 * @param run the run object to save the values to
	 */
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
	/*
	 * Method sets the job one of all six characters to be the same
	 * 
	 * @param firstJob the job to assign to all six characters' job one
	 * @param newRun the run object to save the values to
	 */
	private void setSameJobOneValues(Jobs firstJob, Runs newRun) {
		
		newRun.setVaanJobOne(firstJob);
		newRun.setFranJobOne(firstJob);
		newRun.setBalthierJobone(firstJob);
		newRun.setBaschJobOne(firstJob);
		newRun.setAsheJobOne(firstJob);
		newRun.setPeneloJobOne(firstJob);
	}
	
	/*
	 * Method sets the job two of all six characters to be the same
	 * 
	 * @param secondJob the job to assign to each characters' job two
	 * @param newRun the run object to save the values to
	 */
	private void setSameJobTwoValues(Jobs secondJob, Runs newRun) {
		newRun.setVaanJobTwo(secondJob);
		newRun.setFranJobOne(secondJob);
		newRun.setBalthierJobone(secondJob);
		newRun.setBaschJobOne(secondJob);
		newRun.setAsheJobOne(secondJob);
		newRun.setPeneloJobOne(secondJob);
	}

	/*
	 * Method used to set job one and job two to be the same for each character such that all six characters will have the same job one and job two
	 * 
	 * @param twoJobList ArrayList holding the two unique jobs
	 * @param newRun Runs object to assign the right values to
	 */
	private void setTwoJobs(List<Jobs> twoJobList, Runs newRun) {
		setSameJobOneValues(twoJobList.get(0), newRun);
		setSameJobTwoValues(twoJobList.get(1), newRun);
	}
	
	/*
	 * Method that is used to assign the right job ones for each character where the run is only using job ones for all six
	 * 
	 * @param jobList ArrayList holding the six unique jobs generated
	 * @param newRun Runs object to assign the values to
	 */
	private void setSixUniqueJobs(List<Jobs> jobList, Runs newRun) {
		
		for (int i = 0; i < jobList.size(); i++) {
			setCorrectJobOneCharacter(jobList, i, newRun);
		}
	}
	
	/*
	 * Method that is used to assign each character a unique job one and job two
	 * 
	 * @param doubleJobstList 2D ArrayList holding the generated jobs in the order they need to be assigned
	 * @param newRun Runs object that is the run a user requested
	 */
	private void setTwelveUniqueJobs(ArrayList<ArrayList<Jobs>> doubleJobsList, Runs newRun) {
		for (int i = 0; i < doubleJobsList.size(); i++) {
			//0,0 is vaan, 0,1 is fran, 0,2 balthier
			for (int j = 0; j < doubleJobsList.get(i).size(); j++) {
				setCorrectJobsEachCharacter(doubleJobsList, newRun, i, j);
			}
		}
	}
	
	/*
	 * Method used by the above method setTwelveUniqueJobs to call the right method to assign a character's job one or job two
	 * 
	 * @param doubleJobstList 2D ArrayList holding the jobs
	 * @param newRun Runs object that is a user's requested run
	 * @param index1 used to see which row to pull a job from, 0 is row 1 for job one and 1 is for 2 for job two
	 * @param index2 used to get the correct job one or job two for the character and assign it to them
	 */
	private void setCorrectJobsEachCharacter(ArrayList<ArrayList<Jobs>> doubleJobsList, Runs newRun, int index1, int index2) {
		if (index1 == 0) {
			setCorrectJobOneCharacter(doubleJobsList, newRun, index2);
		}
		
		if (index1 == 1) {
			setCorrectJobTwoCharacter(doubleJobsList, newRun, index2);
		}
	}
	
	/*
	 * Method that sets the job ones of all six characters when they are unique
	 * 
	 * @param doubleJobstList 2D ArrayList that holds the jobs that were generated for a particular run
	 * @param run Runs object that assigns the right jobs to the run a user requested to be generated
	 * @param index	Used to get the right job for the right character job ones
	 */
	private void setCorrectJobOneCharacter(ArrayList<ArrayList<Jobs>> doubleJobsLIst, Runs run, int index) {
		switch (index) {
			case 0 -> run.setVaanJobOne(doubleJobsLIst.get(0).get(index));
			case 1 -> run.setFranJobOne(doubleJobsLIst.get(0).get(index));
			case 2 -> run.setBalthierJobone(doubleJobsLIst.get(0).get(index));
			case 3 -> run.setBaschJobOne(doubleJobsLIst.get(0).get(index));
			case 4 -> run.setAsheJobOne(doubleJobsLIst.get(0).get(index));
			case 5 -> run.setPeneloJobOne(doubleJobsLIst.get(0).get(index));
		}
	}
	
	/*
	 * Method that sets the job twos of all six characters when they are unique
	 * 
	 * @param doubleJobsList 2D ArrayList that holds the jobs generated for a particular run
	 * @param run Runs object that assigns the right jobs to the run a user requested to be generated
	 * @param index	used to get the right job for the right character job twos
	 */
	private void setCorrectJobTwoCharacter(ArrayList<ArrayList<Jobs>> doubleJobsLIst, Runs run, int index) {
		switch (index) {
			case 0 -> run.setVaanJobTwo(doubleJobsLIst.get(1).get(index));
			case 1 -> run.setFranJobTwo(doubleJobsLIst.get(1).get(index));
			case 2 -> run.setBalthierJobTwo(doubleJobsLIst.get(1).get(index));
			case 3 -> run.setBaschJobTwo(doubleJobsLIst.get(1).get(index));
			case 4 -> run.setAsheJobTwo(doubleJobsLIst.get(1).get(index));
			case 5 -> run.setPeneloJobTwo(doubleJobsLIst.get(1).get(index));
		}
	}
	
	/*
	 * Method to set the right job ones for each character when they are unique
	 * 
	 * @param jobsList is a 2D ArrayList that holds the jobs generated in the run
	 * @param run is a Runs object to set the values for the run that the user generated
	 */
	private void setJobOnes(ArrayList<ArrayList<Jobs>> jobsList, Runs run) {
		for (int i = 0; i < jobsList.get(0).size(); i++) {
			setCorrectJobOneCharacter(jobsList, run, i);
		}
	}
	
	/*
	 * Method to set the right job twos for each character for when they are unique
	 * 
	 * @param jobsList is a 2D ArrayList that holds the jobs generated in the run. 
	 * @param run is a Runs object to set the values for the run the user generated
	 */
	private void setJobTwos(ArrayList<ArrayList<Jobs>> jobsList, Runs run) {
		for (int i = 0; i < jobsList.get(1).size(); i++) {
			setCorrectJobTwoCharacter(jobsList, run, i);
		}
	}
}
