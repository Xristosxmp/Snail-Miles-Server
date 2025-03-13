package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.Set;

@Document(collection = "companies")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Chains> chains;

    @JsonProperty("image") // Include this field in the JSON response
    public String getImageBase64() {
        if (image == null) return null;
        return Base64.getEncoder().encodeToString(image); // Convert byte[] to Base64 string
    }
}