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
import org.springframework.data.mongodb.core.mapping.DBRef;


@Document(collection = "offers")
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Offer.OfferBuilder.class)
public class Offer {

    @Id
    private String id;

    private String title;
    private String description;
    private String discount;

    @JsonProperty("required_points")
    private int requiredPoints;

    @JsonIgnore
    @DBRef
    private Chains chain; // MongoDB reference to Chains
}
