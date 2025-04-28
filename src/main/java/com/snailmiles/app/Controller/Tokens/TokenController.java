package com.snailmiles.app.Controller.Tokens;


import com.snailmiles.app.DTO.tokens.TokenResponse;
import com.snailmiles.app.Models.AuthToken;
import com.snailmiles.app.Repositories.AuthTokenRepository;
import com.snailmiles.app.Service.tokens.TokensService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class TokenController {

    private final TokensService tokensService;

    @GetMapping("/generate-token")
    public ResponseEntity<TokenResponse> tokenGeneration() {
        return tokensService.generateToken();
    }


}

