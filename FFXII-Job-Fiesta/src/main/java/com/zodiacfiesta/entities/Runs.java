package com.zodiacfiesta.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "runs")
public class Runs {

	private long runID;
	
	private long userID;
	private String runType;
	private String vaanJobOne;
	
}
