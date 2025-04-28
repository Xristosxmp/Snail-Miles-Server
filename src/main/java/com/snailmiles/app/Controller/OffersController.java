package com.snailmiles.app.Controller;


import com.snailmiles.app.AdminControllers.AdminDTOS.OfferCreateRequest;
import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Repositories.OfferRepository;
import com.snailmiles.app.Repositories.ChainRepository;
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
    public ResponseEntity<?> createOffer(@RequestBody OfferCreateRequest request) {
        Optional<Chains> chainOptional = chainRepository.findById(request.getChain_id());

        if (chainOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Chain not found with ID: " + request.getChain_id());
        }

        Chains chain = chainOptional.get();
        Offer offer = new Offer();
        offer.setTitle(request.getTittle());
        offer.setDescription(request.getDescription());
        offer.setRequiredPoints(request.getRequired_points());
        offer.setDiscount(request.getDiscount());
        offer.setChain(chain);

        offerRepository.save(offer);
        return ResponseEntity.ok("Offer created successfully and linked to chain " + chain.getName());
    }
}

