package com.snailmiles.app.Service.authentication.logout;

import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LogoutService {

    private final UserRepository userRepository;

    public ResponseEntity<?> logout(final String email) {
        User user = userRepository.findByEmail(email);
        System.out.println(email);
        if (user != null) {
            user.setDevice_current_token(null);
            userRepository.save(user);
        } else  return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

}
