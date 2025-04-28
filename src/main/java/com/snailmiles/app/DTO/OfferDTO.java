package com.snailmiles.app.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDTO {
    private String id;
    private String title;
    private String description;
    private String discount;
    @JsonProperty("required_points")
    private int requiredPoints;

    public OfferDTO(String id, String title, String description, int requiredPoints, String discount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requiredPoints = requiredPoints;
        this.discount = discount;
    }
}
