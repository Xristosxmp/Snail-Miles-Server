package com.snailmiles.app.Service.authentication.accountRecovery;

import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryRequest;
import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryResponse;
import com.snailmiles.app.DTO.passwordReset.PasswordResetResponse;
import com.snailmiles.app.DTO.passwordReset.PasswordResetUpdateRequest;
import com.snailmiles.app.DTO.passwordReset.UserPasswordUpdateRequest;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Service.authentication.resetPassword.ResetPasswordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
@Slf4j
public class AccountRecoveryService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResetPasswordService resetPasswordService;



    public ResponseEntity<AccountRecoveryResponse> accountRecoveryExist(@RequestBody AccountRecoveryRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        AccountRecoveryResponse out = new AccountRecoveryResponse();
        if (user != null) {
            out.setStatus(200);
            log.info(user.getEmail() + " is present?");
            return ResponseEntity.ok(out);
        } else {
            out.setStatus(400);
            out.setMessage("Δεν βρέθηκε λογαριασμός με αυτό το email");
            log.info("email not present on recovery email");
            return ResponseEntity.ok(out);
        }
    }



}
