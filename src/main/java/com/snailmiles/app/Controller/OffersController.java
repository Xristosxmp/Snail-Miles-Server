package com.snailmiles.app.Controller;


import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Repo.OfferRepository;
import com.snailmiles.app.Repo.ChainRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/offers")
public class OffersController {

    private final OfferRepository offerRepository;
    private final ChainRepository chainRepository;

    public OffersController(OfferRepository offerRepository, ChainRepository chainRepository) {
        this.offerRepository = offerRepository;
        this.chainRepository = chainRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestParam String title,
                                         @RequestParam String description,
                                         @RequestParam int requiredPoints,
                                         @RequestParam String chainId) {
        Optional<Chains> chainOptional = chainRepository.findById(chainId);

        if (chainOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Chain not found with ID: " + chainId);
        }

        Chains chain = chainOptional.get();
        Offer offer = new Offer();
        offer.setTitle(title);
        offer.setDescription(description);
        offer.setRequiredPoints(requiredPoints);
        offer.setChain(chain);

        offerRepository.save(offer);
        return ResponseEntity.ok("Offer created successfully and linked to chain " + chain.getName());
    }
}

