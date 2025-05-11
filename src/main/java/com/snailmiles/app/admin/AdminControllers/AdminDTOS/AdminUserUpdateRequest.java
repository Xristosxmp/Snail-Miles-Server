package com.snailmiles.app.admin.AdminControllers.AdminDTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AdminUserUpdateRequest {

    @JsonProperty("email")
    private String email;
    private String password;
    private int points;

    @JsonProperty("weekly_points")
    private int weekly_points;

    @JsonProperty("device_current_token")
    private String device_current_token;
}
