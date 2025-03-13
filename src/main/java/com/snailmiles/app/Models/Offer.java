package com.snailmiles.app.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "offers")
@Getter
@Setter
public class Offer {

    @Id
    private String id;

    private String title;
    private String description;
    @JsonProperty("required_points")
    private int requiredPoints;

    @ManyToOne
    @JoinColumn(name = "chain_id", nullable = false)
    @JsonIgnore
    private Chains chain; // Many-to-one relationship with Chain
}