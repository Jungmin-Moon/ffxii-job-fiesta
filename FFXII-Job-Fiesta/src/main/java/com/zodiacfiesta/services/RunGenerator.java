package com.zodiacfiesta.services;

import org.springframework.stereotype.Service;
import com.zodiacfiesta.entities.Jobs;

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

	
	//every character has one different job only
	//each method should return a rowcount to make sure something was inserted or not
	/*
	 * 
	 */
	public int generateOneJobForAll() {
		int rowCount = 0;
		
		
		
		return rowCount;
	}
	
	//every character is same job one job
	
	
	//every character has same job1 and job2
	
	
	//every character has different jobs for job1 and job2
	
	
	//every character has different job1s but same job2
	
	
	//every character has different job2 but same job1
}
