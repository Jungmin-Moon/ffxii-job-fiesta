package com.zodiacfiesta.services;

import org.springframework.stereotype.Service;


//used by RunGenerator to generate Jobs for the run type sent by the user to the controller
@Service
public class JobGenerator {
	
	enum Jobs {UHLAN, SHIKARI, MONK, KNIGHT, BUSHI, WHITE_MAGE, FOEBREAKER, TIME_BATTLEMAGE, MACHINIST,
				RED_BATTLEMAGE, ARCHER, BLACK_MAGE}
	
	
}
