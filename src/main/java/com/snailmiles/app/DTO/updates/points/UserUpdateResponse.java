package com.snailmiles.app.DTO.updates.points;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.login.LoginResponse;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UserUpdateResponse.UserUpdateResponseBuilder.class)
public class UserUpdateResponse {
    private String token;
}
