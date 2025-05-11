package com.snailmiles.app.admin.services.users;

import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import com.snailmiles.app.admin.AdminControllers.AdminDTOS.AdminUserUpdateRequest;
import com.snailmiles.app.admin.responses.AdminUsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AdminUsersResponse getUsersInfo() {
        return AdminUsersResponse.builder().withUsers(userRepository.count()).withUsers_list(userRepository.findAll()).build();
    }

    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/users/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody AdminUserUpdateRequest req) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            userRepository.save(User.builder().withEmail(req.getEmail()).
                    withPassword(passwordEncoder.encode(req.getPassword())).
                    withPoints(req.getPoints()).
                    withWeekly_points(req.getWeekly_points()).
                    withDevice_current_token(req.getDevice_current_token()==null ?
                            null: req.getDevice_current_token().isBlank() ? null : req.getDevice_current_token()).
                    withUpdated_at(new Date())
                    .build());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();

    }


}
