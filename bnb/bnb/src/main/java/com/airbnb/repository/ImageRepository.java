package com.airbnb.repository;

import com.airbnb.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i.url FROM Image i WHERE i.property.id = :propertyId")
    List<String> findByPropertyId(@Param("propertyId") long propertyId);
}
