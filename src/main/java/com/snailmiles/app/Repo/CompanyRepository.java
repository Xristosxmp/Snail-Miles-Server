package com.snailmiles.app.Repo;

import com.snailmiles.app.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, Long> {
    // Correct method name: findByCategoryId (singular)
    List<Company> findByCategoryId(Long categoryId);
}