package com.snailmiles.app.Service.authentication.register;


import com.snailmiles.app.DTO.register.RegisterBodyRequest;
import com.snailmiles.app.Exceptions.RegisterServiceException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Void> register(final RegisterBodyRequest request){

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new RegisterServiceException("Πρόβλημα με την παράμετρο email. Δοκιμάστε ξανά");
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new RegisterServiceException("Το email που χρησιμοποιήσατε υπάρχει ήδη στην βάση δεδομένων μας. Δοκιμάστε κάποιο άλλο!");
        }

        userRepository.save(
                User.builder().
                withEmail(request.getEmail()).
                withPassword(passwordEncoder.encode(request.getPassword())).
                withCreated_at(new Date()).
                withUpdated_at(new Date()).
                withPoints(0).
                withWeekly_points(0)
                .build());

        return ResponseEntity.ok().build();
    }


}
