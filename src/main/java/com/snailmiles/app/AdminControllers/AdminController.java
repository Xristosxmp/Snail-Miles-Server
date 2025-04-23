package com.snailmiles.app.AdminControllers;

import com.snailmiles.app.DTO.AdminUsersResponse;
import com.snailmiles.app.Repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@CrossOrigin
public class AdminController {

    private final UserRepository userRepository;


    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUsersResponse> getUsersInfo() {
        AdminUsersResponse out = new AdminUsersResponse();
        out.setUsers(userRepository.count());
        out.setUsers_list(userRepository.findAll());
        return ResponseEntity.ok(out);
    }


}
