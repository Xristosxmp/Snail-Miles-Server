package com.snailmiles.app.admin.services.offers;

import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Repositories.ChainRepository;
import com.snailmiles.app.Repositories.OfferRepository;
import com.snailmiles.app.admin.AdminControllers.AdminDTOS.OfferCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminOffersService {
    private final OfferRepository offerRepository;
    private final ChainRepository chainRepository;

    public ResponseEntity<Void> createOffer(final OfferCreateRequest request) {

        Optional<Chains> chainOptional = chainRepository.findById(request.getChain_id());

        if (chainOptional.isEmpty()) return ResponseEntity.badRequest().build();

        Chains chain = chainOptional.get();
        offerRepository.save(Offer.builder().
                withChain(chain).
                withTitle(request.getTittle()).
                withDescription(request.getDescription()).
                withDiscount(request.getDiscount()).
                withRequiredPoints(request.getRequired_points()).
                build());
        return ResponseEntity.ok().build();
    }

}
