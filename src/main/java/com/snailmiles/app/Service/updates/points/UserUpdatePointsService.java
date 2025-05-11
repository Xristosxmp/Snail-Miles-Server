package com.snailmiles.app.Service.updates.points;

import com.snailmiles.app.DTO.updates.points.UserUpdateRequest;
import com.snailmiles.app.Exceptions.AccountNotFoundException;
import com.snailmiles.app.Exceptions.InternalErrorException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserUpdatePointsService {
    private final UserRepository userRepository;

    public ResponseEntity<Void> update(final UserUpdateRequest request, final String token) {
        User user = userRepository.findByToken(token);

        if (user != null) {
            try {
                user.setPoints(request.getPoints());
                user.setWeekly_points(request.getWeekly_points());
                userRepository.save(user);

                return ResponseEntity.ok().build();
            } catch (Exception e) {
                throw new InternalErrorException("Προέκυψε σφάλμα κατά το update του χρήστη: " + user.getId());
            }
        } else {
            throw new AccountNotFoundException("Δεν βρέθηκε ο λογαριασμός κατά την ανανέωση πόντων");
        }
    }

}
