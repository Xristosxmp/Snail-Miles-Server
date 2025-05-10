package com.snailmiles.app.DTO.accountExist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = AccountExistRequest.AccountExistRequestBuilder.class)
public class AccountExistRequest {
    private String email;
}
