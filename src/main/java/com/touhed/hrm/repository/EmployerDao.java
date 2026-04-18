package com.touhed.hrm.repository;

import java.util.Optional;

import com.touhed.hrm.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployerDao extends JpaRepository<Employer, Long> {

    Optional<Employer> findByEmail( String email );
}
