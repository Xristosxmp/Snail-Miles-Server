package com.snailmiles.app.DTO.register;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.transactions.TransactionRequest;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = RegisterBodyRequest.RegisterBodyRequestBuilder.class)
public class RegisterBodyRequest {
    private String email;
    private String password;
}
