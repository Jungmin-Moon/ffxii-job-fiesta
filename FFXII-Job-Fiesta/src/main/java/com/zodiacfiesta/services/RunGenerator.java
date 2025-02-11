package com.zodiacfiesta.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.Jobs;
import com.zodiacfiesta.entities.Runs;
import com.zodiacfiesta.repositories.RunsRepository;

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
	
	RunGenerator(JobGenerator jobGenerator, RunsRepository runRepo) {
		this.jobGenerator = jobGenerator;
		this.runRepo = runRepo;
	}
	
	//every character has one different job only
	//each method checks that  the latest run the user has in the table is currently going and the start date is the same as one stored
	//to make sure that the run was added correctly.
	/*
	 * .truncatedTo(ChronoUnit.SECONDS);
	 */
	public boolean generateOneJobForAll() {
		boolean added = false;
		
		Jobs chosenJob = jobGenerator.oneJobEveryone();
		
		Runs oneJobRun = new Runs();
		
		LocalDateTime dateMade = LocalDateTime.now();
		dateMade = dateMade.truncatedTo(ChronoUnit.SECONDS);
		
		LocalDateTime dateCheck = dateMade;
		
		//oneJobRun = setRunValues(chosenJob, oneJobRun, );
		
		return added;
	}
	
	//every character is same job one job
	
	
	//every character has same job1 and job2
	
	
	//every character has different jobs for job1 and job2
	
	
	//every character has different job1s but same job2
	
	
	//every character has different job2 but same job1
	
	
	
	//helper methods
	
}
