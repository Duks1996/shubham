package com.busreservation.repository;

import com.busreservation.entity.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {
    @Query("""
            SELECT DISTINCT sr.seat.id FROM SeatReservation sr
            JOIN StopOrder fromOrder ON sr.fromStopId = fromOrder.stop.id AND fromOrder.bus.id = :busId
            JOIN StopOrder toOrder ON sr.toStopId = toOrder.stop.id AND toOrder.bus.id = :busId
            WHERE sr.date = :date
            AND ( :fromIndex < toOrder.stoporder AND :toIndex > fromOrder.stoporder ) """)
    List<Long> findOccupiedSeatIds(
            @Param("busId") Long busId,
            @Param("date") LocalDate date,
            @Param("fromIndex") int fromIndex,
            @Param("toIndex") int toIndex
    );

}