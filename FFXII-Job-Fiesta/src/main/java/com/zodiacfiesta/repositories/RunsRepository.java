package com.zodiacfiesta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zodiacfiesta.entities.Runs;

@Repository
public interface RunsRepository extends JpaRepository<Runs, Long>{
	
}
