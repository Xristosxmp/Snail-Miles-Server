package com.snailmiles.app.admin.AdminControllers.AdminDTOS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snailmiles.app.Models.Category;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = OfferCreateRequest.OfferCreateRequestBuilder.class)
public class OfferCreateRequest {
    private String chain_id;
    private String tittle;
    private String description;
    private int required_points;
    private String discount;
}
