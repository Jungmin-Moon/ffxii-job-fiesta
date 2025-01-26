package com.zodiacfiesta.services;

import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.Jobs;

import java.util.Random;

//used by RunGenerator to generate Jobs for the run type sent by the user to the controller
@Service
public class JobGenerator {
	
	//Random random = new Random();
	
	//Jobs[] jobs = Jobs.values();
	//jobs[new Random().nextInt(jobs.length)];
	
	//order for jobs should be Vaan, Fran/Balthier, Basch, Ashe, Penelo
	
	//for every character to have a different job1
	public Jobs[] singleDifferentJobs() {
		
	}
	
	//every character getting the same job1 
	public Jobs oneJobEveryone() {
		
	}
	
	//for every character getting the same job1, and same job2
	public Jobs[] oneJobEveryoneBoth() {
		
	}
	
	//for every character having a different job1 and job2
	public Jobs[][] differentBothJobs() {
		
	}
	
	//every character has different job1s but same Job2
	public Jobs[][] differentJobTwo() {
		
	}
	
	//every character different job2s but same Job1
	public Jobs[][] differentJobOne() {
		
	}
}
