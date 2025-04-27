package com.snailmiles.app.Service.authentication.resetPassword;

import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryRequest;
import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryResponse;
import com.snailmiles.app.DTO.passwordReset.PasswordResetResponse;
import com.snailmiles.app.DTO.passwordReset.UserPasswordUpdateRequest;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
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


    public ResponseEntity<PasswordResetResponse> updatePassword(final UserPasswordUpdateRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        PasswordResetResponse out = new PasswordResetResponse();

        if (user != null) {
            if(passwordEncoder.matches(request.getNewPassword(),user.getPassword())){
                out.setStatus(400);
                out.setMessage("Ο νέος κωδικός πρόσβασης δεν μπορεί να είναι ίδιος με τον παλιό");
                return ResponseEntity.ok(out);
            }

            if(passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())){
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userRepository.save(user);
                out.setStatus(200);
                out.setMessage("Ο κωδικός πρόσβασης ενημερώθηκε επιτυχώς!");
            }
            else{
                out.setStatus(400);
                out.setMessage("Λάθος τρέχον κωδικός πρόβασης, δοκιμάστε διαφορετικό κωδικό");
            }


            return ResponseEntity.ok(out);
        } else {
            out.setStatus(500);
            out.setMessage("Ο χρήστης με το email " + request.getEmail() + " δεν βρέθηκε!");
            return ResponseEntity.ok(out);
        }
    }

    public ResponseEntity<AccountRecoveryResponse> recoverPassword(final AccountRecoveryRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        AccountRecoveryResponse out = new AccountRecoveryResponse();

        if (user != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            out.setStatus(200);
            out.setMessage("Ο κωδικός πρόσβασης ενημερώθηκε επιτυχώς!");
            return ResponseEntity.ok(out);
        } else {
            out.setStatus(400);
            out.setMessage("Ο χρήστης με το email " + request.getEmail() + " δεν βρέθηκε!");
            return ResponseEntity.ok(out);
        }
    }


}
