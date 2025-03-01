package com.snailmiles.app.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private String image;
    private Set<CompanyDTO> companies;

    public CategoryDTO(Long id,
                       String name,
                       String image,
                       Set<CompanyDTO> companies) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.companies = companies;
    }
}
