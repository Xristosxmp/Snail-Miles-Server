package com.snailmiles.app.Controller.authentication.login;

import com.snailmiles.app.DTO.AuthRequest;
import com.snailmiles.app.DTO.AuthResponse;
import com.snailmiles.app.DTO.login.LoginRequest;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Service.authentication.login.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/validation")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        return loginService.authenticate(request);
    }

}
