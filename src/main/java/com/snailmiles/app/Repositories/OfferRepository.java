package com.snailmiles.app.Repositories;


import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.Models.Chains;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {
    List<Offer> findByChain(Chains chain);
}

