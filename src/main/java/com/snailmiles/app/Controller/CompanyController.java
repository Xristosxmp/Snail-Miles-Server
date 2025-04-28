package com.snailmiles.app.Controller;

import com.snailmiles.app.Models.Category;
import com.snailmiles.app.Models.Company;
import com.snailmiles.app.Repositories.CategoryRepository;
import com.snailmiles.app.Repositories.CompanyRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;

    public CompanyController(CompanyRepository companyRepository, CategoryRepository categoryRepository) {
        this.companyRepository = companyRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCompany(
            @RequestParam("name") String name,
            @RequestParam("category_id") String categoryId,
            @RequestParam(value = "image", required = true) MultipartFile imageFile) {

        try {
            // Find the category by ID
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isEmpty()) {
                return ResponseEntity.badRequest().body("Category not found with ID: " + categoryId);
            }

            Category category = optionalCategory.get();

            // Create and populate the company
            Company company = new Company();
            company.setName(name);
            company.setCategory(category);

            // Handle the image file if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                company.setImage(imageFile.getBytes());
            }

            // Save to MongoDB
            companyRepository.save(company);

            return ResponseEntity.ok("Company saved successfully");

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }
}
