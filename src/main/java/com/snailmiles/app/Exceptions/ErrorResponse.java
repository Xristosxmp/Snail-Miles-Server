package com.snailmiles.app.Exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.transactions.TransactionRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = ErrorResponse.ErrorResponseBuilder.class)
public class ErrorResponse {
    private int error;
    private String message;
}
