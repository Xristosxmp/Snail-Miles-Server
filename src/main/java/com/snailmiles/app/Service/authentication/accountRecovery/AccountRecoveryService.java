package com.snailmiles.app.Service.authentication.accountRecovery;

import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryRequest;
import com.snailmiles.app.Exceptions.AccountNotFoundException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import com.snailmiles.app.Service.authentication.resetPassword.ResetPasswordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountRecoveryService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResetPasswordService resetPasswordService;



    public ResponseEntity<Void> accountRecoveryExist(final AccountRecoveryRequest request) {
        if(userRepository.findByEmail(request.getEmail()) != null) return ResponseEntity.ok().build();
        throw new AccountNotFoundException("Δεν βρέθηκε λογαριασμός με αυτό το email");
    }



}
