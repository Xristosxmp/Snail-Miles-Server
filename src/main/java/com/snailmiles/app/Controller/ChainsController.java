package com.snailmiles.app.Controller;

import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Company;
import com.snailmiles.app.Repo.ChainRepository;
import com.snailmiles.app.Repo.CompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chains")
public class ChainsController {

    private final ChainRepository chainRepository;
    private final CompanyRepository companyRepository;

    public ChainsController(ChainRepository chainRepository, CompanyRepository companyRepository) {
        this.chainRepository = chainRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public ResponseEntity<?> createChain(@RequestParam String name, @RequestParam String companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if (companyOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Company not found with ID: " + companyId);
        }

        Company company = companyOptional.get();
        Chains chain = new Chains();
        chain.setName(name);
        chain.setCompany(company);

        chainRepository.save(chain);
        return ResponseEntity.ok("Chain created successfully and linked to company " + company.getName());
    }
}

