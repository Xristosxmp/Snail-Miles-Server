package com.snailmiles.app.DTO.shops;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = ChainDTO.ChainDTOBuilder.class)
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
