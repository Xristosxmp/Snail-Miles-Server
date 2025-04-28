package com.snailmiles.app.Service.authentication.register;


import com.snailmiles.app.DTO.register.RegisterBodyRequest;
import com.snailmiles.app.DTO.register.RegisterBodyResponse;
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

    public ResponseEntity<RegisterBodyResponse> register(final RegisterBodyRequest request){
        RegisterBodyResponse out = new RegisterBodyResponse();

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            out.setStatus(400);
            out.setMessage("Πρόβλημα με την παράμετρο email. Δοκιμάστε ξανά");
            return ResponseEntity.ok(out);
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            out.setStatus(400);
            out.setMessage("Το email που χρησιμοποιήσατε υπάρχει ήδη στην βάση δεδομένων μας. Δοκιμάστε κάποιο άλλο!");
            return ResponseEntity.ok(out);
        }

        User new_registed_user = new User();
        new_registed_user.setEmail(request.getEmail());
        new_registed_user.setPassword(passwordEncoder.encode(request.getPassword()));
        new_registed_user.setCreated_at(new Date());
        new_registed_user.setUpdated_at(new Date());
        new_registed_user.setPoints(0);
        new_registed_user.setWeekly_points(0);
        userRepository.save(new_registed_user);


        return ResponseEntity.ok(out);
    }


}
