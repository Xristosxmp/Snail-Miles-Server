package com.snailmiles.app.Controller.authentication.accountExists;

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
public class AccountExistController {
    private final AccountExistService accountExistService;

    @PostMapping("/account")
    public AccountExistResponse checkAccountExist(@RequestBody AccountExistRequest request) {
        return accountExistService.exists(request);
    }
}