package com.zodiacfiesta.entities;

import jakarta.persistence.Column;
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
	@Column(name = "userID")
	private long id;
	
	private String username;
	private String password;
	private String role;
	
	//getters and setter
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	

	
}
