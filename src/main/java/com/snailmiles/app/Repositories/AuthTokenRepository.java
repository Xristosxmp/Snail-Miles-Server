package com.snailmiles.app.Repositories;


import com.snailmiles.app.Models.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends MongoRepository<AuthToken, String> {
    AuthToken findByToken(String token);
}

