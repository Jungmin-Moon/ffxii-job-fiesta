package com.zodiacfiesta.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * A {@code User} object represents a user and their credentials after registering an account with the system.
 * with various credentials such as username which is public knowledge, but id, password, role, firstName and lastName
 * will not be made public information. 
 */

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	private String password;
	private String role;
	
	/*
	 * Fetches the id of the user from the db
	 * @param: no parameters
	 * @return: the user id
	 */
	public long getId() {
		return id;
	}
	
	/*
	 * Sets the user id
	 * @param id: the id to store for this particular user
	 * @return: no return
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/*
	 * Fetches the username of the user from the db
	 * @param: no parameters
	 * @return: the username of the user
	 */
	public String getUsername() {
		return username;
	}
	
	/*
	 * Sets the username for this user
	 * @param username: the username to store into the field value for this value
	 * @return: no return
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/*
	 * Fetches the user password from the db
	 * @param: no parameters
	 * @return: the user's password
	 */
	public String getPassword() {
		return password;
	}
	
	/*
	 * Sets the user's password
	 * @param password: the password to store into the field password
	 * @return: no returns
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
	 * Fetches the user's role that is stored in the db
	 * @param: no parameters
	 * @return: the user's role
	 */
	public String getRole() {
		return role;
	}
	
	/*
	 * Sets the user's role
	 * @param role: the role to store into the field value role
	 * @return: no returns
	 */
	public void setRole(String role) {
		this.role = role;
	}
	

	
}
