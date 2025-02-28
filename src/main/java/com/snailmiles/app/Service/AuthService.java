package com.snailmiles.app.Service;

import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String storedHashedPassword = user.getPassword(); // Already hashed in DB
            String inputHashedPassword = hashPassword(rawPassword); // Hash input

            if (storedHashedPassword.equals(inputHashedPassword)) {
                return user; // Password matches, login successful
            }
        }
        return null; // User not found or incorrect password
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);  // Return null if user not found
    }

    public boolean doesEmailExist(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent(); // Returns true if user exists, false otherwise
    }

    public boolean updatePassword(String email, String newPassword) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(hashPassword(newPassword));
            user.setUpdatedAt(new Date());
            userRepository.save(user);
            return true;
            }

            return false;

    }


    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash); // Convert to hexadecimal string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}


