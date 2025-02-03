package com.zodiacfiesta.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zodiacfiesta.entities.CustomUserDetails;
import com.zodiacfiesta.entities.User;
import com.zodiacfiesta.repositories.UserRepository;

/*
 * A UserService class that implements UserDetailsService to load the user from the database and compare it to the information
 * With methods to get the details of the user and create a user if they don't already exist
 */

@Service
public class UserService implements UserDetailsService{
	
	private UserRepository userRepo;
	
	/*
	 * Constructor for the UserService class
	 * @param userRepo: UserRepository object to query the database
	 * @return: no returns
	 */
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;	
	}
	
	/*
	 * Searches the repository for the user details
	 * @param username: String username to check the repository if the user exists
	 * @return exception: if no user exists in the repository
	 * @return CustomUserDetails: if the user does exist, the information about the user
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.getByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user.");
		}
		
		return new CustomUserDetails(user);
	}
	
	/*
	 * Creates a new user if the user doesn't already exist in the database
	 * @param user: User object that is used to see if the user exists 
	 * @return created: a boolean that tells application if the user was created or not
	 */
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
