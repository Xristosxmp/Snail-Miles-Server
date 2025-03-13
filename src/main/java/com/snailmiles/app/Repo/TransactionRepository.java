package com.snailmiles.app.Repo;


import com.snailmiles.app.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, Long> {
    // You can define custom query methods here if needed
}
