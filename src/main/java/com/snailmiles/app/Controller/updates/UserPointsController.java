package com.snailmiles.app.Controller.updates;

import com.snailmiles.app.DTO.updates.points.UserUpdateRequest;
import com.snailmiles.app.DTO.updates.points.UserUpdateResponse;
import com.snailmiles.app.Service.updates.points.UserUpdatePointsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/points/")
@AllArgsConstructor
public class UserPointsController {

    private final UserUpdatePointsService userUpdatePointsService;

    @PatchMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public UserUpdateResponse updateUserPoints(@RequestBody UserUpdateRequest request, @RequestHeader("Authorization") String auth) {
       return userUpdatePointsService.update(request,auth);
    }

}
