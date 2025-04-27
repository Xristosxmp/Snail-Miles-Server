package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.passwordReset.PasswordResetResponse;
import com.snailmiles.app.DTO.passwordReset.PasswordResetUpdateRequest;
import com.snailmiles.app.DTO.passwordReset.UserPasswordUpdateRequest;
import com.snailmiles.app.DTO.UserUpdateRequest;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Service.ResetPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ResetPasswordService resetPasswordService;

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



    @PatchMapping("/update/reset/password")
    public ResponseEntity<User> updateResetPassword(@RequestBody PasswordResetUpdateRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }

    @PatchMapping(value = "/update/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordResetResponse> updateUserPassword(@RequestBody UserPasswordUpdateRequest request) {
        return resetPasswordService.updatePassword(request);
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
