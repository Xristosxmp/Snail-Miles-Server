package com.snailmiles.app.DTO.shops;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Set;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = CategoryDTO.CategoryDTOBuilder.class)
@AllArgsConstructor
public class CategoryDTO {
    private String id;
    private String name;
    private String image;
    private Set<CompanyDTO> companies;
}
