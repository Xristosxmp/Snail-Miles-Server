package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.Set;

@Document(collection = "categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Company> companies;


    @JsonProperty("image") // Include this field in the JSON response
    public String getImageBase64() {
        if (image == null) return null;
        return Base64.getEncoder().encodeToString(image); // Convert byte[] to Base64 string
    }
}

