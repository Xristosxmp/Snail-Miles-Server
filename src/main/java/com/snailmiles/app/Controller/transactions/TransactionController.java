package com.snailmiles.app.Controller.transactions;



import com.snailmiles.app.DTO.shops.TransactionDTO;
import com.snailmiles.app.DTO.transactions.CreateTransactionResponse;
import com.snailmiles.app.Models.Transaction;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Repositories.TransactionRepository;
import com.snailmiles.app.Repositories.UserRepository;
import com.snailmiles.app.Repositories.ChainRepository;
import com.snailmiles.app.Repositories.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final OfferRepository offerRepository;
    private final ChainRepository chainRepository;


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

    @GetMapping("/info/{uid}")
    public ResponseEntity<List<TransactionDTO>> userTransactions(@PathVariable String uid) {
        List<Transaction> transactions = Optional.ofNullable(transactionRepository.findByUserId(uid))
                .orElse(Collections.emptyList());
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(this::mapToTransactionDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(transactionDTOs);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateTransactionResponse> createTransaction(@RequestBody Map<String, Object> request) {

        CreateTransactionResponse out = new CreateTransactionResponse();

        String user_id = (String) request.get("user_id");
        String offer_id = (String) request.get("offer_id");
        String chain_id = (String) request.get("chain_id");
        Long offerPoints = ((Number) request.get("offer_points")).longValue();
        User user = userRepository.findById(user_id).orElse(null);
        if(user != null){
            int new_points = user.getPoints() - offerPoints.intValue();
            user.setPoints(new_points);
            userRepository.save(user);
            Chains chain = chainRepository.findById(chain_id).get();
            Offer offer = offerRepository.findById(offer_id).get();
            Transaction transaction = new Transaction(user, chain , offer, offerPoints, new Date());
            transactionRepository.save(transaction);
        } else {
            out.setStatus(400);
            out.setMessage("Δεν βρέθηκε ο χρήστης για την δημιουργία συνναλαγής");
        }
        return ResponseEntity.ok(out);
    }
}

