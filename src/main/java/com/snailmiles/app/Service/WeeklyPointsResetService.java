package com.snailmiles.app.Service;

import com.snailmiles.app.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeeklyPointsResetService {

    @Autowired
    private UserRepository userRepository; // Replace with your actual repository

    // Scheduled to run every Monday at 12:01 AM
    @Scheduled(cron = "0 1 0 * * MON") // Cron expression for 12:01 AM every Monday
    public void resetWeeklyPoints() {
        // Update all users' weekly_points to 0
        userRepository.resetWeeklyPoints();
        System.out.println("Weekly points have been reset for all users.");
    }
}

