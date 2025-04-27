package com.snailmiles.app.DTO.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
    @JsonProperty("user_id")
    String user_id;
}
