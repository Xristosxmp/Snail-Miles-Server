package com.snailmiles.app.admin.services.chains;

import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Company;
import com.snailmiles.app.Repositories.ChainRepository;
import com.snailmiles.app.Repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminChainService {
    private final ChainRepository chainRepository;
    private final CompanyRepository companyRepository;

    public List<Chains> getAllChains(){
        return chainRepository.findAll();
    }

    public ResponseEntity<Void> createChain(final String name, final String companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) return ResponseEntity.badRequest().build();
        chainRepository.save(Chains.builder().withName(name).withCompany(companyOptional.get()).build());
        return ResponseEntity.ok().build();
    }

}
