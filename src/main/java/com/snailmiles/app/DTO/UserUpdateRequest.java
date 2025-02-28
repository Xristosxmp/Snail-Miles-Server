package com.snailmiles.app.DTO;

public class UserUpdateRequest {

    private Long id;
    private int points;
    private int weekly_points;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWeeklyPoints() {
        return weekly_points;
    }

    public void setWeeklyPoints(int weeklyPoints) {
        this.weekly_points = weeklyPoints;
    }
}

