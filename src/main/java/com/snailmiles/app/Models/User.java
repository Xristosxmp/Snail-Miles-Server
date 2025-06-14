package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.login.LoginResponse;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;

@Document(collection = "users")
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {

    @Id
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonIgnore
    @JsonProperty("password")
    private String password;

    private int points;

    @JsonProperty("weekly_points")
    private int weekly_points;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("created_at")
    private Date created_at;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("updated_at")
    private Date updated_at;


    @JsonProperty("device_current_token")
    private String device_current_token;

    @JsonProperty("token")
    private String token;

    @JsonProperty("token_expiration")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tokenExpiration;
}
