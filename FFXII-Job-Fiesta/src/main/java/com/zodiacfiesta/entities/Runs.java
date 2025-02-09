package com.zodiacfiesta.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "runs")
public class Runs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long runID;
	
	//userID to know which user created the run and to make searching easier
	//what type of run they chose
	private long userID;
	private String runType;
	
	//storing the Enums as Strings
	@Enumerated(EnumType.STRING)
	private Jobs vaanJobOne;
	@Enumerated(EnumType.STRING)
	private Jobs vaanJobTwo;
	@Enumerated(EnumType.STRING)
	private Jobs peneloJobOne;
	@Enumerated(EnumType.STRING)
	private Jobs peneloJobTwo;
	@Enumerated(EnumType.STRING)
	private Jobs franJobOne;
	@Enumerated(EnumType.STRING)
	private Jobs franJobTwo;
	@Enumerated(EnumType.STRING)
	private Jobs balthierJobone;
	@Enumerated(EnumType.STRING)
	private Jobs balthierJobTwo;
	@Enumerated(EnumType.STRING)
	private Jobs asheJobOne;
	@Enumerated(EnumType.STRING)
	private Jobs asheJobTwo;
	@Enumerated(EnumType.STRING)
	private Jobs baschJobOne;
	@Enumerated(EnumType.STRING)
	private Jobs baschJobTwo;
	
	//the date and time when the run is started or finished
	//dateFinished can be null until finished
	private LocalDateTime dateStarted;
	private LocalDateTime dateFinished;
	
	//MySQL, so this value is TINYINT;
	//This value can only be 0 for false, or 1 for true
	@Column(columnDefinition = "TINYINT default 0")
	private int runFinished;

	//getters and setters
	public long getRunID() {
		return runID;
	}

	public void setRunID(long runID) {
		this.runID = runID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public Jobs getVaanJobOne() {
		return vaanJobOne;
	}

	public void setVaanJobOne(Jobs vaanJobOne) {
		this.vaanJobOne = vaanJobOne;
	}

	public Jobs getVaanJobTwo() {
		return vaanJobTwo;
	}

	public void setVaanJobTwo(Jobs vaanJobTwo) {
		this.vaanJobTwo = vaanJobTwo;
	}

	public Jobs getPeneloJobOne() {
		return peneloJobOne;
	}

	public void setPeneloJobOne(Jobs peneloJobOne) {
		this.peneloJobOne = peneloJobOne;
	}

	public Jobs getPeneloJobTwo() {
		return peneloJobTwo;
	}

	public void setPeneloJobTwo(Jobs peneloJobTwo) {
		this.peneloJobTwo = peneloJobTwo;
	}

	public Jobs getFranJobOne() {
		return franJobOne;
	}

	public void setFranJobOne(Jobs franJobOne) {
		this.franJobOne = franJobOne;
	}

	public Jobs getFranJobTwo() {
		return franJobTwo;
	}

	public void setFranJobTwo(Jobs franJobTwo) {
		this.franJobTwo = franJobTwo;
	}

	public Jobs getBalthierJobone() {
		return balthierJobone;
	}

	public void setBalthierJobone(Jobs balthierJobone) {
		this.balthierJobone = balthierJobone;
	}

	public Jobs getBalthierJobTwo() {
		return balthierJobTwo;
	}

	public void setBalthierJobTwo(Jobs balthierJobTwo) {
		this.balthierJobTwo = balthierJobTwo;
	}

	public Jobs getAsheJobOne() {
		return asheJobOne;
	}

	public void setAsheJobOne(Jobs asheJobOne) {
		this.asheJobOne = asheJobOne;
	}

	public Jobs getAsheJobTwo() {
		return asheJobTwo;
	}

	public void setAsheJobTwo(Jobs asheJobTwo) {
		this.asheJobTwo = asheJobTwo;
	}

	public Jobs getBaschJobOne() {
		return baschJobOne;
	}

	public void setBaschJobOne(Jobs baschJobOne) {
		this.baschJobOne = baschJobOne;
	}

	public Jobs getBaschJobTwo() {
		return baschJobTwo;
	}

	public void setBaschJobTwo(Jobs baschJobTwo) {
		this.baschJobTwo = baschJobTwo;
	}

	public LocalDateTime getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(LocalDateTime dateStarted) {
		this.dateStarted = dateStarted;
	}

	public LocalDateTime getDateFinished() {
		return dateFinished;
	}

	public void setDateFinished(LocalDateTime dateFinished) {
		this.dateFinished = dateFinished;
	}

	public int getRunFinished() {
		return runFinished;
	}

	public void setRunFinished(int runFinished) {
		this.runFinished = runFinished;
	}
	
	
	
}
