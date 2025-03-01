package com.snailmiles.app.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class ChainDTO {
    private Long id;
    private String name;
    private Set<OfferDTO> offers;

    public ChainDTO(Long id, String name, Set<OfferDTO> offers) {
        this.id = id;
        this.name = name;
        this.offers = offers;
    }
}
