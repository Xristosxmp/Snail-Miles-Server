package com.snailmiles.app.DTO.login;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class LoginResponse {
    @Id private String id;
    @JsonProperty("email") private String email;
    @JsonProperty("points") private int points;
    @JsonProperty("weekly_points") private int weekly_points;
    @JsonProperty("status") private int status = 200;
    @JsonProperty("device_current_token") private String device_current_token;
}
