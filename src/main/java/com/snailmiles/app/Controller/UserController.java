package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.PasswordUpdateRequest;
import com.snailmiles.app.DTO.UserUpdateRequest;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired private AuthService authService;

    // Endpoint to update user points and weeklyPoints by ID
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPoints(request.getPoints());
            user.setWeeklyPoints(request.getWeeklyPoints());
            user.setUpdatedAt(new Date());
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
            user.setPassword(hashPassword(request.getPassword()));
            user.setUpdatedAt(new Date());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }




    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash); // Convert to hexadecimal string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }





    // Fetch user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }


}
