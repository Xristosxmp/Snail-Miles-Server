package com.snailmiles.app.AdminControllers.AdminDTOS;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class OfferCreateRequest {
    private String chain_id;
    private String tittle;
    private String description;
    private int required_points;
    private String discount;
}
