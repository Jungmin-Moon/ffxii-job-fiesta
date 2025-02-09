package com.zodiacfiesta.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
	
	private long userID;
	private String runType;
	private Jobs vaanJobOne;
	private Jobs vaanJobTwo;
	private Jobs peneloJobOne;
	private Jobs peneloJobTwo;
	private Jobs franJobOne;
	private Jobs franJobTwo;
	private Jobs balthierJobone;
	private Jobs balthierJobTwo;
	private Jobs asheJobOne;
	private Jobs asheJobTwo;
	private Jobs baschJobOne;
	private Jobs baschJobTwo;
	
	private LocalDateTime dateStarted;
	private LocalDateTime dateFinished;
	
	private boolean runFinished;
}
