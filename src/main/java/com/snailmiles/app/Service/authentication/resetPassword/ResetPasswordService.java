package com.snailmiles.app.Service.authentication.resetPassword;

import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryRequest;
import com.snailmiles.app.DTO.passwordReset.UserPasswordUpdateRequest;
import com.snailmiles.app.Exceptions.AccountNotFoundException;
import com.snailmiles.app.Exceptions.ResetPasswordServiceException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ResetPasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<Void> updatePassword(final UserPasswordUpdateRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user != null) {
            if(passwordEncoder.matches(request.getNewPassword(),user.getPassword())) throw new ResetPasswordServiceException("Ο νέος κωδικός πρόσβασης δεν μπορεί να είναι ίδιος με τον παλιό");
            if(!passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())) throw new ResetPasswordServiceException("Λάθος τρέχον κωδικός πρόβασης, δοκιμάστε διαφορετικό κωδικό");
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } else  throw new AccountNotFoundException("Ο χρήστης δεν βρέθηκε κατά την αλλαγή κωδικού πρόσβασης");

    }

    public ResponseEntity<Void> recoverPassword(final AccountRecoveryRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } throw new AccountNotFoundException("Δεν βρέθηκε λογαριασμός με αυτό το email κατά την επαναφορά κωδικού πρόσβασης");
    }


}
