package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.CategoryDTO;
import com.snailmiles.app.Repo.CategoryRepository;
import com.snailmiles.app.Service.CategoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public ShopController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategoriesWithDetails();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .body(categories);
    }





}

