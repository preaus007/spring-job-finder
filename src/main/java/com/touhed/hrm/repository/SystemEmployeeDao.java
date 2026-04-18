package com.touhed.hrm.repository;

import com.touhed.hrm.entity.SystemEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemEmployeeDao extends JpaRepository<SystemEmployee, Long> {
}
