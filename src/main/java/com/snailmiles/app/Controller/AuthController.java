package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.AuthRequest;
import com.snailmiles.app.DTO.AuthResponse;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired private AuthService authService;
    @Autowired private UserRepository userRepository;
    @PostMapping public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        User user = authService.authenticate(request.getEmail(), request.getPassword());
        if (user != null) return ResponseEntity.ok(new AuthResponse(user));
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }


    @PostMapping("register/exist")
    public ResponseEntity<String> checkEmailExistence(@RequestBody String email) {
        boolean emailExists = authService.doesEmailExist(email);
        System.out.println(email + " GOT " + emailExists);
        if (emailExists) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{status: 400}");
        else return ResponseEntity.ok("{status: 200}");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println(user.getEmail() + " is trying to register");

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is missing");
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        user.setPassword(hashPassword(user.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setPoints(0);
        user.setWeeklyPoints(0);

        // Save user to database
        userRepository.save(user);
        System.out.println(user.getEmail() + " REG");

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
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
}

