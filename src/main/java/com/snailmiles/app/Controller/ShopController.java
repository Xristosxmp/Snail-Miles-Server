package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.shops.CategoryDTO;
import com.snailmiles.app.Repositories.CategoryRepository;
import com.snailmiles.app.Service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategoriesWithDetails();
    }





}

