package com.snailmiles.app.Controller.authentication.accountRecovery;

import com.snailmiles.app.Annotations.SkipSecurity;
import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryRequest;
import com.snailmiles.app.DTO.passwordReset.UserPasswordUpdateRequest;
import com.snailmiles.app.Service.authentication.accountRecovery.AccountRecoveryService;
import com.snailmiles.app.Service.authentication.resetPassword.ResetPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AccountRecoveryController {

    private final AccountRecoveryService accountRecoveryService;
    private final ResetPasswordService resetPasswordService;

    @SkipSecurity
    @PostMapping(value = "/account/recovery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> accountRecovery(@RequestBody AccountRecoveryRequest request) {
        return accountRecoveryService.accountRecoveryExist(request);
    }

    @SkipSecurity
    @PatchMapping(value = "/account/recovery/password/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> recoveryPassword(@RequestBody AccountRecoveryRequest request) {
        return resetPasswordService.recoverPassword(request);
    }

    @PatchMapping(value = "/update/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> resetPassword(@RequestBody UserPasswordUpdateRequest request) {
        return resetPasswordService.updatePassword(request);
    }
}

