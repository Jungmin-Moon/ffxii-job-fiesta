package com.zodiacfiesta.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.Jobs;

/*
 * Class useds to generate the jobs for the player depending on the type of run they request in RunGenerator
 * The Jobs in the list will be added in terms of when the party member permanently joins the party.
 * Vaan, Fran Balthier (for purposes Fran will be listed first then Baltier), Basch, Ashe, Penelo
 * (0,0) and (1,0) will be the jobs for Vaan and so on
 */
@Service
public class JobGenerator {

	Jobs[] jobs = Jobs.values();
	//Jobs[] jobs = Jobs.values();
	//jobs[new Random().nextInt(jobs.length)];
	
	//order for jobs should be Vaan, Fran/Balthier, Basch, Ashe, Penelo
	
	/*
	 * Used to generate a List<Jobs> of six unique jobs Job1(Unique)/(No Job2)
	 * @param: no parameters
	 * @return selected: list of jobs with no duplicates to be returned
	 */
	public List<Jobs> singleDifferentJobs() {
		//One-Dimensional list to add jobs
		ArrayList<Jobs> selected = new ArrayList<>();
		
		//Loop that only loops 6 times total
		for (int i = 0; i < 6; i++) {
			addJobToList(selected);
		}
			
		return selected;
	}
	
	/*
	 * Used to generate a single job for all 6 characters Job1(same)/(No Job2)
	 * @param: no parameters
	 * @return selectedOne: returns a single job
	 */
	public Jobs oneJobEveryone() {
		//randomly selects a single job
		Jobs selectedOne = jobs[new Random().nextInt(jobs.length)];
		
		return selectedOne;
	}
	
	/*
	 * Used to generate two jobs that will be used by everyone Job1(Same)/Job2(Same)
	 * @param: no parameters
	 * @return bothJobs: a list with the two jobs
	 */
	public List<Jobs> oneJobEveryoneBoth() {
		//List to hold the jobs
		ArrayList<Jobs> bothJobs = new ArrayList<>();
		
		//while loop that only adds two jobs to the list.
		while(bothJobs.size() < 2) {
			twoJobsWholeParty(bothJobs);
		}
		
		return bothJobs;
	}
	
	/*
	 * Generates a 2D ArrayList of type Jobs where every character has a different job1, and job2
	 * Job1(Unique)/Job2(Unique)
	 * @param: no parameters
	 * @return twelveFilled: 2D ArrayList listing the order of job1/job2 for each member
	 */
	public ArrayList<ArrayList<Jobs>> differentBothJobs() {
		//A 2D ArrayList of type Jobs
		ArrayList<ArrayList<Jobs>> twelveFilled = new ArrayList<>();
		
		//Each row in the List will have its own ArrayList of type Jobs
		twelveFilled.add(new ArrayList<Jobs>());
		twelveFilled.add(new ArrayList<Jobs>());
		
		//loops through the two rows
		for (int i = 0; i < twelveFilled.size(); i++) {
			
			//loops six times total to get a unique job each time.
			for (int j = 0; j < 6; j++) {
				addJobToList(twelveFilled, i);
			}
		}
		return twelveFilled;
	}
	
	/*
	 * Generates a 2D ArrayList of type Jobs where row 1 will have six unique jobs but row 2 will have one job
	 * Job1 will be unique for each party member but Job2 will be the same for all six characters Job1(Unique)/Job2(same)
	 * @param: no parameters
	 * @return sevenDifferentJobOne: 2D ArrayList with the jobs generated for each member.
	 */
	public ArrayList<ArrayList<Jobs>> differentJobOne() {
		ArrayList<ArrayList<Jobs>> sevenDifferentJobOne = new ArrayList<>();
		
		sevenDifferentJobOne.add(new ArrayList<Jobs>());
		sevenDifferentJobOne.add(new ArrayList<Jobs>());
		
		//Generates a Job to insert into row at index 1 that is the Job2 that will be shared by everyone
		sevenDifferentJobOne.get(1).add(jobs[new Random().nextInt(jobs.length)]);
		
		//this for loop will only add to row 0 of sevenDifferentJobOne
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 6; j++) {
				addJobToList(sevenDifferentJobOne, i);
			}
		}
		
		return sevenDifferentJobOne;
	}
	
	/*
	 * Generates a 2D ArrayList of type Jobs where row 1 will be the same job but row 2 will be six unique jobs
	 * Job2 will be unique but Job1 will be the same for all. Job1(Same)/Job2(Unique)
	 * @param: no parameters
	 * @return sevenDifferentJobTwo: 2D ArrayList with the jobs generated for each member
	 */
	public ArrayList<ArrayList<Jobs>> differentJobTwo() {
		ArrayList<ArrayList<Jobs>> sevenDifferentJobTwo = new ArrayList<>();
		
		sevenDifferentJobTwo.add(new ArrayList<Jobs>());
		sevenDifferentJobTwo.add(new ArrayList<Jobs>());
		
		//Generates a Job to insert into row at index 0 that is the Job1 that will be shared by everyone.
		sevenDifferentJobTwo.get(0).add(jobs[new Random().nextInt(jobs.length)]);
		
		//this for loop will only add to row 1 of sevenDifferentJobTwo
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				addJobToList(sevenDifferentJobTwo, i);
			}
		}
		
		return sevenDifferentJobTwo;
	}
	
	
	//helper methods
	
	/*
	 * Method to generate two jobs 
	 * @param jobsList: list of Jobs that needs to have elements added to it
	 * @return: no returns. Only adds the job to the list
	 */
	private void twoJobsWholeParty(ArrayList<Jobs> jobsList) {
		Jobs potentialJob = jobs[new Random().nextInt(jobs.length)];
		
		while (checkIfAlreadySelected(potentialJob, jobsList) == true) {
			potentialJob = jobs[new Random().nextInt(jobs.length)];
		}
		
		jobsList.add(potentialJob);
	}
	
	/*
	 * Helper method that will be overloaded that takes a job chosen and a list
	 * and checks whether the job is already in the list
	 * @param checkJob: a Jobs object that is to be checked
	 * @param aJobList: a one-dimensional ArrayList that is going to be checked
	 * @return isAlreadyAdded: Boolean that returns whether the job is already in the list
	 */
	private boolean checkIfAlreadySelected(Jobs checkJob, List<Jobs> aJobList) {
		
		boolean isAlreadyAdded = false;
		
		//Due to any list being a maximum size of 12 possible elements
		if (aJobList.contains(checkJob)) {
			isAlreadyAdded = true;
		}
		
		return isAlreadyAdded;
	}
	
	/*
	 * Helper method  to check if the Job chosen from the enum wasn't already added. 
	 * @param checkjob: Jobs object that is to be checked
	 * @param list: the list that is searched 
	 * @returns isAlreadyAdded: boolean that returns whether the job is already in there (true) or isn't in there (false)
	 */
	private boolean checkIfAlreadySelected(Jobs checkJob, ArrayList<ArrayList<Jobs>> list) {
		boolean isAlreadyAdded = false;
		
		//should be ok since the max in the list can be 12 
		if (list.contains(checkJob)) {
			isAlreadyAdded = true;
		}
		
		return isAlreadyAdded;
	}
	
	/*
	 * Helper method to add a job to the list that will also be overloaded
	 * @param aJobs: list that needs jobs added to them
	 * @param index: the index to add the job
	 * @return: no returns
	 */
	private void addJobToList(ArrayList<ArrayList<Jobs>> aJobs, int index) {
		
		//randomly chooses a job in the ENUM
		Jobs potentialJob = jobs[new Random().nextInt(jobs.length)];
		
		//if the job isn't in the list then keep randomly choosing a job until it is a job not in the list already
		while (checkIfAlreadySelected(potentialJob, aJobs) == true) {
			potentialJob = jobs[new Random().nextInt(jobs.length)];
		}
		
		//add the job to the list 
		aJobs.get(index).add(potentialJob);
	}
	
	/*
	 * Helper method that is overloaded to take only a one-dimensional List<Jobs> and add the Job if it isn't already in it
	 * @param aJob: Jobs object that will potentially be added
	 * @param aJobList: one-dimensional List<Jobs> that will have Jobs added to it
	 * @return: no returns. Only used to add elements to list
	 */
	private void addJobToList(List<Jobs> aJobList) {
		
		Jobs potentialJob = jobs[new Random().nextInt(jobs.length)];
		
		while (checkIfAlreadySelected(potentialJob, aJobList) == true) {
			potentialJob = jobs[new Random().nextInt(jobs.length)];
		}
		
		aJobList.add(potentialJob);
	}
	
	/* code to remind myself how to properly do what i need
	 * for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                nums.get(i).add(j);
            }
        }
        
        System.out.println("Values in 2D List before Mod");
        for (ArrayList<Integer> row : nums) {
            for (Integer i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        
        modList(nums);
        
        System.out.println("Values in 2D List after");
        for (ArrayList<Integer> row : nums) {
            for (Integer i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    
    
    public static void modList(ArrayList<ArrayList<Integer>> n) {
        for (int i = 0; i < n.size(); i++) {
            for (int j = 0; j < n.get(i).size(); j++) {
                int temp = n.get(i).get(j);
                n.get(i).set(j, temp * 2);
            }
        }
    }
	 * 
	 * 
	 * 
	 */
}
