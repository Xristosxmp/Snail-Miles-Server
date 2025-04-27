package com.snailmiles.app.Controller.authentication.logout;


import com.snailmiles.app.DTO.logout.LogoutRequest;
import com.snailmiles.app.Service.authentication.logout.LogoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logout")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class LogoutController {

    private final LogoutService logoutService;


    @PatchMapping("/service")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest req) {
        return logoutService.logout(req);
    }

}
