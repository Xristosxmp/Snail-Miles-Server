package com.snailmiles.app.admin;

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
