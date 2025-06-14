package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Base64;
import java.util.Set;

@Document(collection = "categories")
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Category.CategoryBuilder.class)
public class Category {
    @Id
    private String id;

    private String name;

    @Field("image")
    private byte[] image;

    @DBRef
    private Set<Company> companies;

    @JsonProperty("image")
    public String getImageBase64() {
        if (image == null) return null;
        return Base64.getEncoder().encodeToString(image); // Convert byte[] to Base64 string
    }
}
