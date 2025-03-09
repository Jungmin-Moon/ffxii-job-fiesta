package com.zodiacfiesta.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zodiacfiesta.repositories.RunsRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	RunsRepository runsRepo;
	
	HomeController(RunsRepository runsRepo) {
		this.runsRepo = runsRepo;
	}
	
	@GetMapping() 
	public String home(Authentication auth, Model model) {
		var lastTenStarted = runsRepo.getLastTenAdded();
		var lastTenFinished = runsRepo.getLastTenFinished();
		
		UserDetails user = (UserDetails) auth.getPrincipal();
		
		model.addAttribute("startedRuns", lastTenStarted);
		model.addAttribute("finishedRuns", lastTenFinished);
		
		return "home.html";
	}
	
	
	
}
