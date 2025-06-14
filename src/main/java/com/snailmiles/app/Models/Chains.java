package com.snailmiles.app.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Set;

@Document(collection = "chains")
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Chains.ChainsBuilder.class)
public class Chains {
    @Id
    private String id;

    private String name;

    @JsonIgnore
    @DBRef
    private Company company; // MongoDB reference to Company (if not embedded)

    @DBRef
    private Set<Offer> offers; // MongoDB reference to Offer (if not embedded)
}
