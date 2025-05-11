package com.snailmiles.app.admin.services.chains;

import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Repositories.ChainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminChainService {
    private final ChainRepository chainRepository;
    public List<Chains> getAllChains(){
        return chainRepository.findAll();
    }
}
