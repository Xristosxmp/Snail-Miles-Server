package com.snailmiles.app.Exceptions.expiredApiKey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = NewApiResponse.NewApiResponseBuilder.class)
public class NewApiResponse {
    private String token;
}
