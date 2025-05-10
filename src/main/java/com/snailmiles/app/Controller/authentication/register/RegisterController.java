package com.snailmiles.app.Controller.authentication.register;


import com.snailmiles.app.Annotations.SkipSecurity;
import com.snailmiles.app.DTO.register.RegisterBodyRequest;
import com.snailmiles.app.Service.authentication.register.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin
@AllArgsConstructor
@Slf4j
@SkipSecurity
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/request")
    public ResponseEntity<Void> register(@RequestBody RegisterBodyRequest request) {
        return registerService.register(request);
    }




}
