package com.snailmiles.app.DTO.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.updates.points.UserUpdateRequest;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TransactionRequest.TransactionRequestBuilder.class)
public class TransactionRequest {
    @JsonProperty("user_id")
    String user_id;
}
