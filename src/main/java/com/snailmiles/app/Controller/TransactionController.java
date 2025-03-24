package com.snailmiles.app.Controller;



import com.snailmiles.app.DTO.TransactionDTO;
import com.snailmiles.app.Models.Transaction;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Repo.TransactionRepository;
import com.snailmiles.app.Repo.UserRepository;
import com.snailmiles.app.Repo.ChainRepository;
import com.snailmiles.app.Repo.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired private UserRepository userRepository; // Inject UserRepository to access DB
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private OfferRepository offerRepository;
    @Autowired private ChainRepository chainRepository;


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

        // Create the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

        // Return the response with the headers
        return ResponseEntity.ok()
                .headers(headers)
                .body(transactionDTOs);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody Map<String, Object> request) {
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
            return ResponseEntity.ok("User points updated to " + new_points);
        }
        return ResponseEntity.badRequest().build();
    }
}

