package com.snailmiles.app.Controller;

import com.snailmiles.app.DTO.AuthRequest;
import com.snailmiles.app.DTO.AuthResponse;
import com.snailmiles.app.DTO.LogoutRequest;
import com.snailmiles.app.DTO.register.CredentialsRequest;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/validation")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        try {
            User user = authService.authenticate(
                    request.getEmail(),
                    request.getPassword(),
                    request.getCurrent_device_token()
            );
            return ResponseEntity.ok(new AuthResponse(user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        System.out.println("logout user email: " + request.getEmail());
        return authService.logout(request.getEmail());
    }



    @PostMapping("/register/exist")
    public ResponseEntity<String> checkEmailExistence(@RequestBody String email) {
        boolean emailExists = authService.doesEmailExist(email);
        System.out.println(email + " GOT " + emailExists);
        if (emailExists) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{status: 400}");
        else return ResponseEntity.ok("{status: 200}");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CredentialsRequest request) {
        log.info(request.getEmail() + " is trying to register");
        log.info(request.getPassword() + " password");


        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is missing");
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User new_registed_user = new User();
        new_registed_user.setEmail(request.getEmail());
        new_registed_user.setPassword(passwordEncoder.encode(request.getPassword()));
        new_registed_user.setCreated_at(new Date());
        new_registed_user.setUpdated_at(new Date());
        new_registed_user.setPoints(0);
        new_registed_user.setWeekly_points(0);

        userRepository.save(new_registed_user);


        return ResponseEntity.ok().body("User registered successfully");
    }


}

