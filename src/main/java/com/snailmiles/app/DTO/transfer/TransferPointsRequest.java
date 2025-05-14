package com.snailmiles.app.DTO.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.DTO.accountExist.AccountExistRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = TransferPointsRequest.TransferPointsRequestBuilder.class)
public class TransferPointsRequest {
    private String user_accept_id;
    private String secret_key;
}
