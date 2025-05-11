package com.snailmiles.app.Service.authentication.accountExist;

import com.snailmiles.app.DTO.accountExist.AccountExistRequest;
import com.snailmiles.app.DTO.accountExist.AccountExistResponse;
import com.snailmiles.app.Exceptions.AccountAlreadyExistsException;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountExistService {

    private final UserRepository userRepository;

    public ResponseEntity<Void> exists(final AccountExistRequest req) {
        if (userRepository.findByEmail(req.getEmail()) != null) throw new AccountAlreadyExistsException("Το email που χρησιμοποιήσατε υπάρχει ήδη στην βάση δεδομένων μας. Δοκιμάστε κάποιο άλλο!");
        return ResponseEntity.ok().build();
    }

}
