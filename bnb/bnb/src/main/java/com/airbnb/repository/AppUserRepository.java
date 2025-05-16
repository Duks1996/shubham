package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findByEmail(String email);
    public Optional<AppUser> findByUsername(String username);
    public Optional<AppUser> findByEmailOrUsername(String username, String email);
}