package com.snailmiles.app.Repo;

import com.snailmiles.app.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.weekly_points = 0")
    void resetWeeklyPoints();
}