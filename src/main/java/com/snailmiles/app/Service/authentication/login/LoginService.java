package com.snailmiles.app.Service.authentication.login;

import com.snailmiles.app.DTO.login.LoginBadResponse;
import com.snailmiles.app.DTO.login.LoginRequest;
import com.snailmiles.app.DTO.login.LoginResponse;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> authenticate(final LoginRequest request) {

        LoginBadResponse bad_response = new LoginBadResponse();
        LoginResponse out = new LoginResponse();

        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                if(user.getDevice_current_token() == null){
                    user.setDevice_current_token(request.getCurrent_device_token());
                    userRepository.save(user);
                }
                if(user.getDevice_current_token() == null) user.setDevice_current_token(request.getCurrent_device_token());
                if (!request.getCurrent_device_token().equals(user.getDevice_current_token())) {
                    bad_response.setMessage("Αυτός ο λογαριασμός είναι ήδη συνδεδεμένος σε διαφορετική συσκευή. Παρακαλώ αποσυνδεθείτε από αυτή για να συνεχίσετε.");
                    return ResponseEntity.ok(bad_response);
                }


                out.setId(user.getId());
                out.setEmail(user.getEmail());
                out.setPoints(user.getPoints());
                out.setWeekly_points(user.getWeekly_points());
                out.setDevice_current_token(request.getCurrent_device_token());
                return ResponseEntity.ok(out);
            }
        }
        return ResponseEntity.ok(bad_response);
    }

}


