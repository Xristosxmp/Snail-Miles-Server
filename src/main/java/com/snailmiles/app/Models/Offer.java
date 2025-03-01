package com.snailmiles.app.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int requiredPoints;

    @ManyToOne
    @JoinColumn(name = "chain_id", nullable = false)
    @JsonIgnore
    private Chains chain; // Many-to-one relationship with Chain
}