package com.snailmiles.app.DTO;

import com.snailmiles.app.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminUsersResponse {
    long users;
    List<User> users_list;
}
