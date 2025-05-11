package com.snailmiles.app.admin.services.categories;

import com.snailmiles.app.Models.Category;
import com.snailmiles.app.Repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AdminCategoriesService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<Void> createCategory(
            @RequestParam("name") String name,
            @RequestParam(value = "image", required = true) MultipartFile imageFile) {

        if (name == null || name.isEmpty()) return ResponseEntity.badRequest().build();
        try {
            categoryRepository.save(Category.builder().withName(name).withImage(imageFile.getBytes()).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}
