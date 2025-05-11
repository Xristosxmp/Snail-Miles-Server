package com.snailmiles.app.Service.authentication.login;

import com.snailmiles.app.Config.SecurityConfig;
import com.snailmiles.app.DTO.login.LoginBadResponse;
import com.snailmiles.app.DTO.login.LoginRequest;
import com.snailmiles.app.DTO.login.LoginResponse;
import com.snailmiles.app.Exceptions.UnauthorizedException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse authenticate(final LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                if(user.getDevice_current_token() == null){
                    user.setDevice_current_token(request.getCurrent_device_token());
                    userRepository.save(user);
                }

                if (!request.getCurrent_device_token().equals(user.getDevice_current_token())) {
                    throw new UnauthorizedException("Αυτός ο λογαριασμός είναι ήδη συνδεδεμένος σε διαφορετική συσκευή. Παρακαλώ αποσυνδεθείτε από αυτή για να συνεχίσετε.");
                }

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, 1);
                user.setTokenExpiration(calendar.getTime());
                user.setToken(SecurityConfig.generateNewToken());
                userRepository.save(user);
                return LoginResponse.builder().
                        withId(user.getId()).
                        withEmail(user.getEmail()).
                        withPoints(user.getPoints()).
                        withWeekly_points(user.getWeekly_points()).
                        withDevice_current_token(request.getCurrent_device_token()).withToken(user.getToken())
                        .build();
            }
        }
        log.info("Δεν βρέθηκε λογαριασμός με αυτό το όνομα και κωδικό");
        throw new UnauthorizedException("Δεν βρέθηκε λογαριασμός με αυτό το όνομα και κωδικό");
    }





}


