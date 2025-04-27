package com.snailmiles.app.DTO.categories;

import com.snailmiles.app.DTO.CategoryDTO;
import com.snailmiles.app.Models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriesResponse {
    private int status = 200;
    private String message = "success";
    private List<CategoryDTO> categories;
}
