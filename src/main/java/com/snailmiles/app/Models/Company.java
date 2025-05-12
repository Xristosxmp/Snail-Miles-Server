package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.bson.types.Binary;

import java.util.Base64;
import java.util.Set;

@Document(collection = "companies")
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Company.CompanyBuilder.class)
public class Company {
    @Id
    private String id;

    private String name;

    @Field("image")
    private byte[] image; // Using Binary to store image data as bytes

    @DBRef
    private Category category; // MongoDB reference to Category (if not embedded)

    @DBRef
    private Set<Chains> chains; // MongoDB reference to Chains (if not embedded)

    @JsonProperty("image") // Include this field in JSON response
    public String getImageBase64() {
        return image != null ? Base64.getEncoder().encodeToString(image) : null;
    }
}
