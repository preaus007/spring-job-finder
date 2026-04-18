package com.touhed.hrm.repository;

import java.util.Optional;

import com.touhed.hrm.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Long> {

    Optional<City> findByCityName( String cityName );
}
