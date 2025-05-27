package com.busreservation.repository;

import com.busreservation.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT s FROM Seat s WHERE s.bus.id = :busId")
    List<Seat> findAllByBusId(@Param("busId") Long busId);

}