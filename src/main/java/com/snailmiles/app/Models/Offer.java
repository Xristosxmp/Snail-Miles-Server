package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@Document(collection = "offers")
public class Offer {

    @Id
    private String id;

    private String title;
    private String description;

    @JsonProperty("required_points")
    private int requiredPoints;

    @JsonIgnore
    @DBRef
    private Chains chain; // MongoDB reference to Chains
}
