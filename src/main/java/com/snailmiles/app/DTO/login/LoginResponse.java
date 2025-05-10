package com.snailmiles.app.DTO.login;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = LoginResponse.LoginResponseBuilder.class)
public class LoginResponse {
    @Id private String id;
    @JsonProperty("email") private String email;
    @JsonProperty("points") private int points;
    @JsonProperty("weekly_points") private int weekly_points;
    @JsonProperty("device_current_token") private String device_current_token;
    @JsonProperty("token") private String token;

}
