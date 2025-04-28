package com.snailmiles.app.Service.authentication.accountExist;

import com.snailmiles.app.DTO.accountExist.AccountExistRequest;
import com.snailmiles.app.DTO.accountExist.AccountExistResponse;
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

    public ResponseEntity<AccountExistResponse> exists(final AccountExistRequest req) {
        int status = userRepository.findByEmail(req.getEmail()) != null ? 400 : 200;
        AccountExistResponse out = new AccountExistResponse();
        out.setStatus(status);
        if(status == 400) out.setMessage("Το email που χρησιμοποιήσατε υπάρχει ήδη στην βάση δεδομένων μας. Δοκιμάστε κάποιο άλλο!");
        return ResponseEntity.ok(out);
    }

}
