package com.snailmiles.app.Service.tokens;

import com.snailmiles.app.DTO.tokens.TokenResponse;
import com.snailmiles.app.Models.AuthToken;
import com.snailmiles.app.Repositories.AuthTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class TokensService {

    private final AuthTokenRepository authTokenRepository;

    public ResponseEntity<TokenResponse> generateToken(){
        String token = generateRandomToken();
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);

        authTokenRepository.save(authToken);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                authTokenRepository.delete(authToken);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Send the token to the user
        return ResponseEntity.ok(new TokenResponse(token));
    }
    // Method to generate a secure random token
    private String generateRandomToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }

}
