package com.snailmiles.app.Repo;


import com.snailmiles.app.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    @Transactional
    @Query("{}, { $set: { weekly_points: 0 } }")
    void resetWeeklyPoints();
}
