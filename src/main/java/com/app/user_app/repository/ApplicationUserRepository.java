package com.app.user_app.repository;

import com.app.user_app.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Boolean existsByEmail(String email);

    Optional<ApplicationUser> findByEmail(String email);
}