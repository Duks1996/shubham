package com.busreservation.repository;

import com.busreservation.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StopRepository extends JpaRepository<Stop, Long> {
    Optional<Stop> findByName(@Param("name") String name);
}