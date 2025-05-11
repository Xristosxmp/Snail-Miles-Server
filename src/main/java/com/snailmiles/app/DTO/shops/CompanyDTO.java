package com.snailmiles.app.DTO.shops;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class CompanyDTO {
    private String id;
    private String name;
    private String image;
    private Set<ChainDTO> chains;

    public CompanyDTO(String id, String name, String image, Set<ChainDTO> chains) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.chains = chains;
    }
}
