package com.snailmiles.app.DTO.transactions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionResponse {
    private int status = 200;
    private String message = "Η συνναλαγή έγινε επιτυχώς";
}
