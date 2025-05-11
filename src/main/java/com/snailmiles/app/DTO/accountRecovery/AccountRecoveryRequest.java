package com.snailmiles.app.DTO.accountRecovery;

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
@JsonDeserialize(builder = AccountRecoveryRequest.AccountRecoveryRequestBuilder.class)
public class AccountRecoveryRequest {
    private String email;
    private String password;
}
