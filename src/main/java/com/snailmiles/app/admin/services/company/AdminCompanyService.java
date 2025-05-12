package com.snailmiles.app.admin.services.company;

import com.snailmiles.app.Models.Category;
import com.snailmiles.app.Models.Company;
import com.snailmiles.app.Repositories.CategoryRepository;
import com.snailmiles.app.Repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminCompanyService {
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;

    public ResponseEntity<Void> createCompany(
            final String name,
            final String categoryId,
            final MultipartFile imageFile) {

        try {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isEmpty()) return ResponseEntity.badRequest().build();
            companyRepository.save(Company.builder().withName(name).withCategory(optionalCategory.get()).withImage(imageFile.getBytes()).build());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
