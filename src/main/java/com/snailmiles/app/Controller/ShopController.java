package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.CategoryDTO;
import com.snailmiles.app.DTO.categories.CategoriesResponse;
import com.snailmiles.app.Repositories.CategoryRepository;
import com.snailmiles.app.Service.CategoryService;
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
    public ResponseEntity<CategoriesResponse> getAllCategories() {

        CategoriesResponse out = new CategoriesResponse();
        List<CategoryDTO> categories = categoryService.getAllCategoriesWithDetails();

        if(categories.isEmpty()){
            out.setStatus(400);
            out.setMessage("Δεν βρέθηκαν κατηγορίες καταστημάτων");
        } else out.setCategories(categories);


        return ResponseEntity.ok(out);
    }





}

