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
    private String id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Chains> chains;

    // Ensure this field is stored in MongoDB
    @JsonProperty("category_id")
    public String getCategoryId() {
        return category != null ? category.getId() : null;
    }

    @JsonProperty("image") // Include this field in JSON response
    public String getImageBase64() {
        return image != null ? Base64.getEncoder().encodeToString(image) : null;
    }
}