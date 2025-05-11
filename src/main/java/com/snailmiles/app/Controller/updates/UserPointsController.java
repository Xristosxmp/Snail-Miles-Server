package com.snailmiles.app.Controller.updates;

import com.snailmiles.app.DTO.updates.points.UserUpdateRequest;
import com.snailmiles.app.Service.updates.points.UserUpdatePointsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/points")
@AllArgsConstructor
@Slf4j
public class UserPointsController {

    private final UserUpdatePointsService userUpdatePointsService;

    @PatchMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUserPoints(@RequestBody UserUpdateRequest request, @RequestHeader("Authorization") String auth) {
       log.info("token: " +auth.substring(0,10));
       return userUpdatePointsService.update(request,auth);
    }

}
