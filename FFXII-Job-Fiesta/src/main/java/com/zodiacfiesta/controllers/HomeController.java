package com.zodiacfiesta.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zodiacfiesta.entities.Runs;
import com.zodiacfiesta.repositories.RunsRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	RunsRepository runsRepo;
	
	HomeController(RunsRepository runsRepo) {
		this.runsRepo = runsRepo;
	}
	
	@GetMapping() 
	public String home() {
		List<Runs> lastTenStarted = runsRepo.getLastTenAdded();
		List<Runs> lastTenFinished = runsRepo.getLastTenFinished();
		
		
		
		return "home.html";
	}
	
	
	
}
