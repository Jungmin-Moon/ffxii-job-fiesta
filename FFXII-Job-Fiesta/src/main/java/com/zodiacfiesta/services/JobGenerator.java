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
	
	//for every character to have a different job1
	public List<Jobs> singleDifferentJobs() {
		ArrayList<Jobs> selected = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			Jobs temp = jobs[new Random().nextInt(jobs.length)];
			
			if (selected.contains(temp)) {
				continue;
			} else {
				selected.add(temp);
			}
		}
			
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
		
		for (int i = 0; i < 2; i++) {
			Jobs temp = jobs[new Random().nextInt(jobs.length)];
			
			if (bothJobs.contains(temp)) {
				continue;
			} else {
				bothJobs.add(temp);
			}
		}
		
		
		return bothJobs;
	}
	
	//for every character having a different job1 and job2
	public List<List<Jobs>> differentBothJobs() {
		
	}
	
	//every character has different job1s but same Job2
	public List<List<Jobs>> differentJobTwo() {
		
	}
	
	//every character different job2s but same Job1
	public List<List<Jobs>> differentJobOne() {
		
	}
}
