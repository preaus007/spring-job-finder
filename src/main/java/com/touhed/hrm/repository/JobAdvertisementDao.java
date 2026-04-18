package com.touhed.hrm.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.touhed.hrm.entity.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Long> {

	List<JobAdvertisement> findByActiveTrue();

	List<JobAdvertisement> findByEmployerIdAndActiveTrue( Long employerId );

	List<JobAdvertisement> findAllByOrderByApplicationDeadlineAsc();

	@Query( "SELECT j FROM JobAdvertisement j WHERE j.applicationDeadline = ?1" )
	List<JobAdvertisement> findByApplicationDeadline( LocalDateTime deadline );
}
