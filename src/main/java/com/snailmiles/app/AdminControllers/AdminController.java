package com.snailmiles.app.AdminControllers;

import com.snailmiles.app.AdminControllers.AdminDTOS.AdminUserUpdateRequest;
import com.snailmiles.app.DTO.AdminUsersResponse;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@CrossOrigin
public class AdminController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUsersResponse> getUsersInfo() {
        AdminUsersResponse out = new AdminUsersResponse();
        out.setUsers(userRepository.count());
        out.setUsers_list(userRepository.findAll());
        return ResponseEntity.ok(out);
    }

    @DeleteMapping("/users/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping(value = "/users/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody AdminUserUpdateRequest req) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User user = existingUserOptional.get();

            user.setEmail(req.getEmail());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setPoints(req.getPoints());
            user.setWeekly_points(req.getWeekly_points());
            if(req.getDevice_current_token() != null)
                if(req.getDevice_current_token().isBlank() || req.getDevice_current_token().isEmpty())
                        user.setDevice_current_token(null);
                else user.setDevice_current_token(req.getDevice_current_token());
            user.setUpdated_at(new Date());


            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }



}
