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
	private String firstName;
	private String lastName;
	
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
	
	/*
	 * Fetches the user's firstName from the db
	 * @param: no parameters
	 * @return: the user's firstName 
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/*
	 * Sets the user's firstName
	 * @param firstName: the String to store into the firstName
	 * @return: no returns
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/*
	 * Fetches the user's lastName from the db
	 * @param: no parameters
	 * @return: lastName of the user 
	 */
	public String getLastName() {
		return lastName;
	}
	
	/*
	 * Sets the user's lastName
	 * @param lastName: the String to store into the lastName
	 * @return: no returns
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
