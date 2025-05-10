package com.snailmiles.app.Service.updates.points;

import com.snailmiles.app.Config.SecurityConfig;
import com.snailmiles.app.DTO.updates.points.UserUpdateRequest;
import com.snailmiles.app.DTO.updates.points.UserUpdateResponse;
import com.snailmiles.app.Exceptions.AccountNotFoundException;
import com.snailmiles.app.Exceptions.InternalErrorException;
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

    public UserUpdateResponse update(final UserUpdateRequest request, final String token) {
        User user = userRepository.findByToken(token);

        if (user != null) {
            try {
                user.setPoints(request.getPoints());
                user.setWeekly_points(request.getWeekly_points());
                String new_token = SecurityConfig.generateNewToken();
                user.setToken(new_token);
                userRepository.save(user);

                return UserUpdateResponse.builder().withToken(new_token).build();
            } catch (Exception e) {
                throw new InternalErrorException("Προέκυψε σφάλμα κατά το update του χρήστη: " + user.getId());
            }
        } else {
            throw new AccountNotFoundException("");
        }
    }

}
