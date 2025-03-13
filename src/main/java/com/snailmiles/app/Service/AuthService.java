package com.snailmiles.app.Service;

import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public User authenticate(String email, String rawPassword, String deviceToken) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        System.out.println(email);
        System.out.println(rawPassword);
        System.out.println(deviceToken);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                if(user.getDevice_current_token() == null){
                    user.setDevice_current_token(deviceToken);
                    userRepository.save(user);
                }
                if(user.getDevice_current_token() == null) user.setDevice_current_token(deviceToken);
                if (!deviceToken.equals(user.getDevice_current_token())) {
                    throw new RuntimeException("Αυτός ο λογαριασμός είναι ήδη συνδεδεμένος σε διαφορετική συσκευή. Παρακαλώ αποσυνδεθείτε από αυτή για να συνεχίσετε.");
                }
                return user;
            }
        }

        throw new RuntimeException("Λάθος email ή κωδικός πρόσβασης");
    }

    public ResponseEntity<?> logout(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        System.out.println(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setDevice_current_token(null);
            userRepository.save(user);
        } else  return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }



    public User getUserById(Long id) {
        return userRepository.findById(String.valueOf(id)).orElse(null);
    }

    public boolean doesEmailExist(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent(); // Returns true if user exists, false otherwise
    }

    public boolean updatePassword(String email, String newPassword) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setUpdated_at(new Date());
            userRepository.save(user);
            return true;
            }

            return false;

    }


}


