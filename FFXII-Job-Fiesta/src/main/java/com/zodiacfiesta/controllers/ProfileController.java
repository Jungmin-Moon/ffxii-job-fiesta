package com.zodiacfiesta.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zodiacfiesta.services.RunGenerator;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	RunGenerator runGen;
	
	ProfileController(RunGenerator runGen) {
		this.runGen = runGen;
	}

	//when the user logs in they will see their profile
	@GetMapping()
	public String profile(Authentication auth, Model model) {
		UserDetails user = (UserDetails) auth.getPrincipal();
		
		//Display User information, basiclaly just username on the left side somewhere
		//Need to display current run if there is any and show past completed runs as well these will be displayed in the middle of the page
		//the form to generate a run will be a drop down menu with a submit button
		//A link above the form will list the types of each run in a separate HTML
		
		
		
		return "profile.html";
	}
	
	//the post mapping will redirect to the profile after generating the run for the user
	@PostMapping()
	public String profileAfterRunGen(Authentication auth, Model model, @RequestParam(required = false) String runType) {
		UserDetails user = (UserDetails) auth.getPrincipal();
		
		boolean runCreated = false;
		
		runCreated = runGen.profileControllerHelper(runType, user.getUsername());
		
		if (runCreated == true) {
			
		}
		
		return "profile.html";
	}
}
