package com.snailmiles.app.Repositories;

import com.snailmiles.app.Models.Category;
import com.snailmiles.app.Models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String> {
    // Correct method name: findByCategoryId (singular)
    List<Company> findByCategoryId(String categoryId);
    List<Company> findByCategory(Category category);
}