package com.snailmiles.app.DTO.logout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = LogoutRequest.LogoutRequestBuilder.class)
public class LogoutRequest {
    private String token;
}
