package com.snailmiles.app.Controller.authentication.accountExists;

import com.snailmiles.app.Annotations.SkipSecurity;
import com.snailmiles.app.DTO.accountExist.AccountExistRequest;
import com.snailmiles.app.DTO.accountExist.AccountExistResponse;
import com.snailmiles.app.Service.authentication.accountExist.AccountExistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exist")
@CrossOrigin
@AllArgsConstructor
@Slf4j
@SkipSecurity
public class AccountExistController {
    private final AccountExistService accountExistService;

    @PostMapping("/account")
    public ResponseEntity<Void> checkAccountExist(@RequestBody AccountExistRequest request) {
        return accountExistService.exists(request);
    }
}