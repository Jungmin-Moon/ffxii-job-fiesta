package com.zodiacfiesta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zodiacfiesta.entities.Runs;

import jakarta.transaction.Transactional;

@Repository
public interface RunsRepository extends JpaRepository<Runs, Long>{
	
	//order for jobs should be Vaan, Fran/Balthier, Basch, Ashe, Penelo
	
}
