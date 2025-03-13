package com.snailmiles.app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    private String id;
    private int points;
    private int weekly_points;
}

