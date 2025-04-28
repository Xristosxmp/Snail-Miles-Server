package com.snailmiles.app.Service.authentication.logout;

import com.snailmiles.app.DTO.logout.LogoutRequest;
import com.snailmiles.app.DTO.logout.LogoutResponse;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LogoutService {

    private final UserRepository userRepository;

    public ResponseEntity<LogoutResponse> logout(final LogoutRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getId());
        LogoutResponse out = new LogoutResponse();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setDevice_current_token(null);
            userRepository.save(user);
        }else out.setStatus(400);
        return ResponseEntity.ok(out);
    }

}
