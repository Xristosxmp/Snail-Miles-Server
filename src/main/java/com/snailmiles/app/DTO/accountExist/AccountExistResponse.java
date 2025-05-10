package com.snailmiles.app.DTO.accountExist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = AccountExistResponse.AccountExistResponseBuilder.class)
public class AccountExistResponse {
    private int status;
    private String message;
}
