package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.property=:property AND r.appUser=:appUser")
    Review findByAppUserAndProperty(@Param("appUser") AppUser appUser, @Param("property") Property property);

    @Query("SELECT r FROM Review r WHERE r.appUser=:appUser")
    List<Review> findByAppUser(@Param("appUser") AppUser appUser);
}