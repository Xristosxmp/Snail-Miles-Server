package com.snailmiles.app.admin.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.accountRecovery.AccountRecoveryRequest;
import com.snailmiles.app.Models.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = AdminUsersResponse.AdminUsersResponseBuilder.class)
public class AdminUsersResponse {
    long users;
    List<User> users_list;
}
