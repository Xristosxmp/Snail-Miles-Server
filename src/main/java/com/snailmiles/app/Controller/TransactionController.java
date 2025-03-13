package com.snailmiles.app.Controller;


import com.snailmiles.app.DTO.TransactionDTO;
import com.snailmiles.app.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint to get all transactions for a specific userId
    @GetMapping("/user/{userId}")
    public List<TransactionDTO> getTransactionsByUserId(@PathVariable String userId) {
        return transactionService.getTransactionsWithDetails(userId);
    }
}
