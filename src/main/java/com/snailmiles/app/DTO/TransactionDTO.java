package com.snailmiles.app.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDTO {

    private String transactionId;
    private User user;
    private Chains chain;
    private Offer offer;
    private Long offerPoints;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("created_at")
    private Date created_at;

    // Constructor
    public TransactionDTO(String transactionId, User user, Chains chain, Offer offer, Long offerPoints, Date created_at) {
        this.transactionId = transactionId;
        this.user = user;
        this.chain = chain;
        this.offer = offer;
        this.offerPoints = offerPoints;
        this.created_at = created_at;
    }
}

