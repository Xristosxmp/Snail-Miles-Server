package com.snailmiles.app.Service.authentication.logout;

import com.snailmiles.app.DTO.logout.LogoutRequest;
import com.snailmiles.app.Exceptions.AccountNotFoundException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LogoutService {
    private final UserRepository userRepository;
    public ResponseEntity<Void> logout(final LogoutRequest request) {
        User user = userRepository.findByToken(request.getToken());
        if(user == null) throw new AccountNotFoundException("Δεν βρέθηκε ο λογαρισμός");
        user.setDevice_current_token(null);
        user.setToken(null);
        user.setTokenExpiration(null);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}
