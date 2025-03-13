package com.snailmiles.app.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class ChainDTO {
    private String id;
    private String name;
    private Set<OfferDTO> offers;

    public ChainDTO(String id, String name, Set<OfferDTO> offers) {
        this.id = id;
        this.name = name;
        this.offers = offers;
    }
}
