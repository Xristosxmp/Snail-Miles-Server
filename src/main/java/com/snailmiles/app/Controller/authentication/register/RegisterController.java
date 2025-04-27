package com.snailmiles.app.Controller.authentication.register;


import com.snailmiles.app.DTO.register.RegisterBodyRequest;
import com.snailmiles.app.DTO.register.RegisterBodyResponse;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Service.authentication.register.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/request")
    public ResponseEntity<RegisterBodyResponse> register(@RequestBody RegisterBodyRequest request) {
        return registerService.register(request);
    }




}
