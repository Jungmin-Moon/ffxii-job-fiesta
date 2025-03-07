package com.zodiacfiesta.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zodiacfiesta.entities.Runs;

@Repository
public interface RunsRepository extends JpaRepository<Runs, Long>{
	
	//order for jobs should be Vaan, Fran/Balthier, Basch, Ashe, Penelo
	
	@Query("SELECT r FROM Runs r WHERE r.userID = :userId AND r.runFinished = 0")
	public Runs checkIfOngoing(long userId);
	
	
	@Query("SELECT r FROM Runs r WHERE r.userID = :userId AND r.dateStarted = :dateMade AND r.runType = :runType AND r.runFinished = 0")
	public Runs checkRunAdded(long userId, LocalDateTime dateMade, String runType);
	
	@Query("SELECT r FROM Runs r WHERE r.runFinished = 0 ORDER BY r.dateStarted DESC LIMIT 10")
	public List<Runs> getLastTenAdded();
	
	@Query("SELECT r FROM Runs r WHERE r.runFinished = 1 ORDER BY r.dateFinished DESC LIMIT 10")
	public List<Runs> getLastTenFinished();
}
