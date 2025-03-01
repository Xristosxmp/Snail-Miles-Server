package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user", indexes = {@Index(name = "idx_user_id", columnList = "id")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    private String email;

    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    private String password;



    private int points;
    @JsonProperty("weekly_points")
    private int weekly_points;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("created_at")
    @Temporal(TemporalType.DATE)
    private Date created_at;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("updated_at")
    @Temporal(TemporalType.DATE)
    private Date updated_at;


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getPoints() {return points;}
    public void setPoints(int points) {this.points = points;}
    public int getWeeklyPoints() {return weekly_points;}
    public void setWeeklyPoints(int weeklyPoints) {this.weekly_points = weeklyPoints;}
    public Date getCreatedAt() {return created_at;}
    public void setCreatedAt(Date createdAt) {this.created_at = createdAt;}
    public Date getUpdatedAt() {return updated_at;}
    public void setUpdatedAt(Date updatedAt) {this.updated_at = updatedAt;}
}
