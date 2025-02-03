package com.zodiacfiesta.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.Jobs;

/*
 * Class useds to generate the jobs for the player depending on the type of run they request in RunGenerator
 */
@Service
public class JobGenerator {
	
	//Random random = new Random();
	Jobs[] jobs = Jobs.values();
	//Jobs[] jobs = Jobs.values();
	//jobs[new Random().nextInt(jobs.length)];
	
	//order for jobs should be Vaan, Fran/Balthier, Basch, Ashe, Penelo
	
	//methods for generating
	

	/*
	 * Used to generate a List<Jobs> of six unique jobs
	 * @param: no parameters
	 * @return selected: list of jobs with no duplicates to be returned
	 */
	public List<Jobs> singleDifferentJobs() {
		ArrayList<Jobs> selected = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			addJobToList(selected);
		}
			
		return selected;
	}
	
	/*
	 * Used to generate a single job for all 6 characters
	 * @param: no parameters
	 * @return selectedOne: the job that every character must be
	 */
	public Jobs oneJobEveryone() {
		Jobs selectedOne = jobs[new Random().nextInt(jobs.length)];
		
		return selectedOne;
	}
	
	/*
	 * Used to generate two jobs that will be used by everyone
	 * @param: no parameters
	 * @return bothJobs: a list with the two jobs that everyone must be job1/job2 for the entire game
	 */
	public List<Jobs> oneJobEveryoneBoth() {
		ArrayList<Jobs> bothJobs = new ArrayList<>();
		
		while(bothJobs.size() < 2) {
			twoJobsWholeParty(bothJobs);
		}
		
		return bothJobs;
	}
	
	/*
	 * Generates a 2D ArrayList of type Jobs where every character has a different job1, and job2
	 * Which character gets which job will be random in the order they become permanent party members
	 * @param: no parameters
	 * @return twelveFilled: 2D ArrayList listing the order of job1/job2 for each member
	 */
	public ArrayList<ArrayList<Jobs>> differentBothJobs() {
		ArrayList<ArrayList<Jobs>> twelveFilled = new ArrayList<>();
		
		twelveFilled.add(new ArrayList<Jobs>());
		twelveFilled.add(new ArrayList<Jobs>());
		
		for (int i = 0; i < twelveFilled.size(); i++) {
			
			for (int j = 0; j < 6; j++) {
				//Jobs temp = jobs[new Random().nextInt(jobs.length)];
				addJobToList(twelveFilled, i);
			}
		}
		return twelveFilled;
	}
	
	//every character has different job1s but same Job2
	public ArrayList<ArrayList<Jobs>> differentJobTwo() {
		
	}
	
	//every character different job2s but same Job1
	public ArrayList<ArrayList<Jobs>> differentJobOne() {
		
	}
	
	
	
	
	//helper methods
	
	/*
	 * Helper method to generate 6 different jobs and add to a list
	 * @param aJobs: list where the jobs will be added
	 * @return: no returns. Just used to add elements to list
	 */
	private void generateSixUniqueJobs(List<Jobs> aJobs) {
		
		Jobs potentialJob = jobs[new Random().nextInt(jobs.length)];
		
		while (checkIfAlreadySelected(potentialJob, aJobs) == true) {
			
		}
	}
	
	//helper method for adding just 2 jobs
	private void twoJobsWholeParty(ArrayList<Jobs> jobsList) {
		
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
