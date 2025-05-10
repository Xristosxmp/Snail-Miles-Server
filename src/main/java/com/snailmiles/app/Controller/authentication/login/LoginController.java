package com.snailmiles.app.Controller.authentication.login;

import com.snailmiles.app.Annotations.SkipSecurity;
import com.snailmiles.app.DTO.login.LoginRequest;
import com.snailmiles.app.DTO.login.LoginResponse;
import com.snailmiles.app.Service.authentication.login.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
@AllArgsConstructor
@Slf4j
@SkipSecurity
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/validation",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse authenticate(@RequestBody LoginRequest request) {
        return loginService.authenticate(request);
    }

}
