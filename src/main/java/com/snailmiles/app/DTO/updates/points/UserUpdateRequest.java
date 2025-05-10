package com.snailmiles.app.DTO.updates.points;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UserUpdateRequest.UserUpdateRequestBuilder.class)
public class UserUpdateRequest {
    private int points;
    private int weekly_points;
}

