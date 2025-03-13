package com.snailmiles.app.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;

    @JsonProperty("current_device_token")
    private String current_device_token;
}

