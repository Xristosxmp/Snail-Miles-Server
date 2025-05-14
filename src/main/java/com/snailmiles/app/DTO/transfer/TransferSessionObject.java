package com.snailmiles.app.DTO.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TransferPointsRequest.TransferPointsRequestBuilder.class)
public class TransferSessionObject {
    private String uuid;
    private String secret_key;
    private String points_to_be_transfered;
}
