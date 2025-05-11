package com.snailmiles.app.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Document(collection = "transactions")
@Data
public class Transaction {

    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Chains chain;  // Reference to Chain object

    @DBRef
    private Offer offer;  // Reference to Offer object

    private Long offerPoints;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("created_at")
    private Date createdAt;

    // Constructors
    public Transaction() {}

    public Transaction(User user, Chains chain, Offer offer, Long offerPoints, Date createdAt) {
        this.user = user;
        this.chain = chain;
        this.offer = offer;
        this.offerPoints = offerPoints;
        this.createdAt = createdAt;
    }
}
