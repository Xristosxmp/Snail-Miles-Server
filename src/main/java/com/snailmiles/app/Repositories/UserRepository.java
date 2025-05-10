package com.snailmiles.app.Repositories;


import com.snailmiles.app.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    @Transactional
    @Query("{}, { $set: { weekly_points: 0 } }")
    void resetWeeklyPoints();

    List<User> findAll();
    long count();

    User findByToken(String token);
}
