package com.snailmiles.app.DTO;

import com.snailmiles.app.Models.User;

import java.util.Date;


public class AuthResponse {
    private Long id;
    private String email;
    private int points;
    private int weekly_points;
    private Date created_at;
    private Date updated_at;

    public AuthResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.points = user.getPoints();
        this.weekly_points = user.getWeeklyPoints();
        this.created_at = user.getCreatedAt();
        this.updated_at = user.getUpdatedAt();
    }

    // Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public int getPoints() { return points; }
    public int getWeeklyPoints() { return weekly_points; }
    public Date getCreatedAt() { return created_at; }
    public Date getUpdatedAt() { return updated_at; }
}

