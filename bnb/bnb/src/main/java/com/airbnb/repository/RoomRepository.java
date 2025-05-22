package com.airbnb.repository;

import com.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Room r WHERE r.id = :roomId AND r.property.id = :propertyId")
    int deleteByIdAndPropertyId(@Param("roomId") long roomId,@Param("propertyId") long propertyId);

    @Query("SELECT r FROM Room r WHERE r.type = :roomType AND r.property.id = :propertyId")
    Room findByTypeAndPropertyId(@Param("roomType") String roomType,@Param("propertyId") long propertyId);
}