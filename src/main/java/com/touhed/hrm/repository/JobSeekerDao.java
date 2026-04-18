package com.touhed.hrm.repository;

import java.util.Optional;

import com.touhed.hrm.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Long> {

    Optional<JobSeeker> findByEmail( String email );
    Optional<JobSeeker> findByNationalId( String nationalId );
}
