package com.snailmiles.app.Repositories;
import com.snailmiles.app.Models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {}
