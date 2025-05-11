package com.snailmiles.app.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tokens")
@Data
public class AuthToken {
    @Id
    private String _id;
    private String token;
}
