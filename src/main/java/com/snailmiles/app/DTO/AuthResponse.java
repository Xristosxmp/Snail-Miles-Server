package com.snailmiles.app.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snailmiles.app.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthResponse {
    private String id;
    private String email;
    private int points;

    @JsonProperty("weekly_points")
    private int weekly_points;

    @JsonProperty("created_at")
    private Date created_at;

    @JsonProperty("updated_at")
    private Date updated_at;

    @JsonProperty("device_current_token")
    private String device_current_token;

    public AuthResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.points = user.getPoints();
        this.weekly_points = user.getWeekly_points();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();
        this.device_current_token = user.getDevice_current_token();
    }
}

