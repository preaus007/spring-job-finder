package com.touhed.hrm.repository;

import java.util.Optional;

import com.touhed.hrm.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositonDao extends JpaRepository<JobPosition, Long> {
   
	Optional<JobPosition> findByTitle( String title );
}
