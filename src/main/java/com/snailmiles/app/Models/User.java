package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
@Getter
@Setter
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
}
