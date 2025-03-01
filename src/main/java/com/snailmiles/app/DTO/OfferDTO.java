package com.snailmiles.app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDTO {
    private Long id;
    private String title;
    private String description;
    private int requiredPoints;

    public OfferDTO(Long id, String title, String description, int requiredPoints) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requiredPoints = requiredPoints;
    }
}
