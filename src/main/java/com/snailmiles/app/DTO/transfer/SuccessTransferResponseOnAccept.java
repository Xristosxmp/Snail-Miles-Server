package com.snailmiles.app.DTO.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SuccessTransferResponseOnAccept.SuccessTransferResponseOnAcceptBuilder.class)
public class SuccessTransferResponseOnAccept {
    private int status;
    private String message;
    private int new_points;
}
