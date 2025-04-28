package com.snailmiles.app.Service.updates.points;

import com.snailmiles.app.DTO.updates.points.UserUpdateRequest;
import com.snailmiles.app.DTO.updates.points.UserUpdateResponse;
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
public class UserUpdatePointsService {
    private final UserRepository userRepository;

    public ResponseEntity<UserUpdateResponse> update(final UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getId());
        UserUpdateResponse out = new UserUpdateResponse();
        if (userOptional.isPresent()) {
            try {
                User user = userOptional.get();
                user.setPoints(request.getPoints());
                user.setWeekly_points(request.getWeekly_points());
                userRepository.save(user);
                out.setStatus(200);
                return ResponseEntity.ok(out);
            } catch (Exception e) {
                out.setStatus(400);
                return ResponseEntity.ok(out);
            }
        } else {
            out.setStatus(400);
            out.setMessage("Δεν βρέθηκε ο χρήστης");
            return ResponseEntity.ok(out);
        }
    }

}
