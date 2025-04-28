package com.snailmiles.app.Repositories;

import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChainRepository extends MongoRepository<Chains, String> {
    List<Chains> findByCompany(Company company);
}