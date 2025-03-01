package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class Chains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Company company; // Many-to-one relationship with Company

    @OneToMany(mappedBy = "chain", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Offer> offers; // One-to-many relationship with Offer
}
