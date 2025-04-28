package com.snailmiles.app.Service;


import com.snailmiles.app.DTO.TransactionDTO;
import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Models.Transaction;
import com.snailmiles.app.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Method to fetch all transactions for a given userId and include details about Offer and Chain
    public List<TransactionDTO> getTransactionsWithDetails(String userId) {
        List<Transaction> transactions = Optional.ofNullable(transactionRepository.findByUserId(userId))
                .orElse(Collections.emptyList());

        return transactions.stream()
                .map(this::mapToTransactionDTO)
                .collect(Collectors.toList());
    }

    // Helper method to map Transaction to TransactionDTO
    private TransactionDTO mapToTransactionDTO(Transaction transaction) {
        // Extracting necessary details from the Transaction object
        Chains chain = transaction.getChain() != null ? transaction.getChain() : null;
        Offer offer = transaction.getOffer() != null ? transaction.getOffer() : null;
        Long offerPoints = transaction.getOfferPoints();

        return new TransactionDTO(
                transaction.getId(),
                transaction.getUser(),
                chain,
                offer,
                offerPoints,
                new Date()
        );
    }
}
