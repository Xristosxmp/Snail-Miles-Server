package com.snailmiles.app.Repo;


import com.snailmiles.app.Models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    @Transactional
    @Query("{}, { $set: { weekly_points: 0 } }")
    void resetWeeklyPoints();

    List<User> findAll();
    long count();
}
