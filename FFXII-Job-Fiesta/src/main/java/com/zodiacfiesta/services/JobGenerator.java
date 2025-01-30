package com.zodiacfiesta.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.Jobs;

//used by RunGenerator to generate Jobs for the run type sent by the user to the controller
@Service
public class JobGenerator {
	
	//Random random = new Random();
	Jobs[] jobs = Jobs.values();
	//Jobs[] jobs = Jobs.values();
	//jobs[new Random().nextInt(jobs.length)];
	
	//order for jobs should be Vaan, Fran/Balthier, Basch, Ashe, Penelo
	
	//methods for generating
	
	//for every character to have a different job1
	public List<Jobs> singleDifferentJobs() {
		ArrayList<Jobs> selected = new ArrayList<>();
		
		
			
		return selected;
	}
	
	//every character getting the same job1 
	public Jobs oneJobEveryone() {
		Jobs selectedOne = jobs[new Random().nextInt(jobs.length)];
		
		return selectedOne;
	}
	
	//for every character getting the same job1, and same job2
	public List<Jobs> oneJobEveryoneBoth() {
		ArrayList<Jobs> bothJobs = new ArrayList<>();
		
		while(bothJobs.size() < 2) {
			twoJobsWholeParty(bothJobs);
		}
		
		return bothJobs;
	}
	
	//for every character having a different job1 and job2
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
	
	//helper method for adding just 2 jobs
	private void twoJobsWholeParty(ArrayList<Jobs> jobsList) {
		
	}
	
	//helper method to check if a job was already selected
	private boolean checkIfAlreadySelected(Jobs checkJob, ArrayList<ArrayList<Jobs>> list) {
		boolean isAlreadyAdded = false;
		
		//should be ok since the max in the list can be 12 
		if (list.contains(checkJob)) {
			isAlreadyAdded = true;
		}
		
		return isAlreadyAdded;
	}
	
	//adds jobs to the List
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
