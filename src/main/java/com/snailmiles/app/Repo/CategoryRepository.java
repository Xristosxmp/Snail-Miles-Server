package com.snailmiles.app.Repo;
import com.snailmiles.app.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
