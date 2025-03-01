package com.snailmiles.app.Repo;

import com.snailmiles.app.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // Correct method name: findByCategoryId (singular)
    List<Company> findByCategoryId(Long categoryId);
}