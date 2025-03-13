package com.snailmiles.app.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long chainId;
    private Long offerId;
    private Long offerPoints;

    @JsonFormat(pattern = "yyyy-MM-dd")  // Format the date in the desired pattern
    @JsonProperty("created_at")  // Map the field name in JSON to "created_at"
    @Temporal(TemporalType.DATE)  // Store only the date (no time)
    private Date createdAt;

    // Constructors
    public Transaction() {}

    public Transaction(Long userId, Long chainId, Long offerId, Long offerPoints, Date createdAt) {
        this.userId = userId;
        this.chainId = chainId;
        this.offerId = offerId;
        this.offerPoints = offerPoints;
        this.createdAt = createdAt;
    }
}
