package com.snailmiles.app.Service.category;

import com.snailmiles.app.DTO.shops.CategoryDTO;
import com.snailmiles.app.DTO.shops.ChainDTO;
import com.snailmiles.app.DTO.shops.CompanyDTO;
import com.snailmiles.app.DTO.shops.OfferDTO;
import com.snailmiles.app.Exceptions.categories.CategoriesException;
import com.snailmiles.app.Models.*;
import com.snailmiles.app.Repositories.CategoryRepository;
import com.snailmiles.app.Repositories.ChainRepository;
import com.snailmiles.app.Repositories.CompanyRepository;
import com.snailmiles.app.Repositories.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final ChainRepository chainRepository;
    private final OfferRepository offerRepository;

    public List<CategoryDTO> getAllCategoriesWithDetails() {
        List<Category> categories = Optional.ofNullable(categoryRepository.findAll())
                .orElse(Collections.emptyList());

        if(categories.isEmpty()) throw new CategoriesException("Δεν βρέθηκαν κατηγορίες καταστημάτων");

        return categories.stream()
                .map(this::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    private CategoryDTO mapToCategoryDTO(Category category) {
        if (category == null) return null;

        // Fetch companies where category_id matches
        Set<CompanyDTO> companyDTOs = Optional.ofNullable(companyRepository.findByCategory(category))
                .orElse(Collections.emptyList()) // Use List instead of Set to prevent issues
                .stream()
                .map(this::mapToCompanyDTO)
                .collect(Collectors.toSet());

        return new CategoryDTO(
                category.getId(),
                Optional.ofNullable(category.getName()).orElse("Unknown Category"),
                Optional.ofNullable(category.getImageBase64()).orElse(""),
                companyDTOs
        );
    }

    private CompanyDTO mapToCompanyDTO(Company company) {
        if (company == null) return null;

        Set<ChainDTO> chainDTOs = Optional.ofNullable(chainRepository.findByCompany(company))
                .orElse(Collections.emptyList()) // Fetch chains from MongoDB
                .stream()
                .map(this::mapToChainDTO)
                .collect(Collectors.toSet());

        return new CompanyDTO(
                company.getId(),
                Optional.ofNullable(company.getName()).orElse("Unknown Company"),
                Optional.ofNullable(company.getImageBase64()).orElse(""),
                chainDTOs
        );
    }

    private ChainDTO mapToChainDTO(Chains chain) {
        if (chain == null) return null;

        List<Offer> offs = offerRepository.findByChain(chain);
        // Sort the list by requiredPoints
        Set<OfferDTO> offerDTOs = offs.stream()
                .sorted(Comparator.comparingInt(Offer::getRequiredPoints)) // Sort by requiredPoints (low to high)
                .map(this::mapToOfferDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return new ChainDTO(
                chain.getId(),
                Optional.ofNullable(chain.getName()).orElse("Unknown Chain"),
                offerDTOs
        );
    }

    private OfferDTO mapToOfferDTO(Offer offer) {
        if (offer == null) return null;

        return new OfferDTO(
                offer.getId(),
                Optional.ofNullable(offer.getTitle()).orElse("No Title"),
                Optional.ofNullable(offer.getDescription()).orElse("No Description"),
                Optional.ofNullable(offer.getRequiredPoints()).orElse(0),
                Optional.ofNullable(offer.getDiscount()).orElse("?%")
        );
    }
}