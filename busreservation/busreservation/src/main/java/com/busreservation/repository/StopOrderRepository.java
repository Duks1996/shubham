package com.busreservation.repository;

import com.busreservation.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StopOrderRepository extends JpaRepository<StopOrder, Long> {

    @Query("SELECT so FROM StopOrder so WHERE so.bus = :bus AND so.stop = :stop")
    Optional<StopOrder> findByBusAndStop(@Param("bus") Bus bus, @Param("stop") Stop stop);

}