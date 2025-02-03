package com.zodiacfiesta.entities;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * A {@code CustomUserDetails} object that represents the UserDetails of the user
 */

public class CustomUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	/*
	 * Constructor for CustomUserDetails
	 * @param user: a User user object to store into the user field
	 * @return: returns nothing
	 */
	public CustomUserDetails(User user) {
		this.user = user;
	}
	
	/*
	 * Sets the authority of the user when they login
	 * @param: no parameters
	 * @return: returns the list of authorities the user has
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
		
		return Arrays.asList(authority);
	}
	
	/*
	 * Gets the user's password
	 * @param: no parameters
	 * @return: no returns
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	/*
	 * Gets the username of the user
	 * @param: no parameters
	 * @return: no returns
	 */
	@Override
	public String getUsername() {
		return user.getUsername();
	}
}
