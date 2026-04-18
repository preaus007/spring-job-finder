package com.touhed.hrm.repository;

import java.util.List;
import java.util.Optional;

import com.touhed.hrm.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationDao extends JpaRepository<JobApplication, Long> {

	Optional<JobApplication> findByJobAdvertisement_IdAndJobSeeker_Id( Long jobAdvertisementId, Long jobSeekerId );

	List<JobApplication> findByJobAdvertisement_Id( Long jobAdvertisementId );

	List<JobApplication> findByJobSeeker_Id( Long jobSeekerId );

}