package com.zodiacfiesta.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.CustomUserDetails;
import com.zodiacfiesta.entities.User;
import com.zodiacfiesta.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;	
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.getByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user.");
		}
		
		return new CustomUserDetails(user);
	}
	
	public boolean createUser(User user) {
		boolean created = true;
		
		String checkUsername = user.getUsername();
		
		if (userRepo.getByUsername(checkUsername) == null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			
			user.setPassword(encodedPassword);
			user.setRole("USER");
			
			userRepo.save(user);
			
		} else {
			created = false;
		}
		
		return created;
	}
}
