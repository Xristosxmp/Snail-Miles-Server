package com.snailmiles.app.DTO.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;

    @JsonProperty("current_device_token")
    private String current_device_token;
}
