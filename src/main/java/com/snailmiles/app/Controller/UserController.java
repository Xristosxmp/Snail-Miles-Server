package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.PasswordUpdateRequest;
import com.snailmiles.app.DTO.UserUpdateRequest;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // Endpoint to update user points and weeklyPoints by ID
    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getId());
        if (userOptional.isPresent()) {
            try {
                User user = userOptional.get();
                user.setPoints(request.getPoints());
                user.setWeekly_points(request.getWeekly_points());
                user.setUpdated_at(new Date());
                userRepository.save(user);
                return ResponseEntity.ok().build(); // 200 OK if successful
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error if something goes wrong
            }
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if user doesn't exist
        }
    }


    @PutMapping("/update/offline")
    public ResponseEntity<User> updateUserOffile(@RequestBody UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(String.valueOf(request.getId()));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPoints(user.getPoints() + request.getPoints());
            user.setWeekly_points(user.getWeekly_points() + request.getWeekly_points());
            user.setUpdated_at(new Date());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }



    @PutMapping("/update/password")
    public ResponseEntity<User> updatePassword(@RequestBody PasswordUpdateRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setUpdated_at(new Date());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }






    // Fetch user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }


}
