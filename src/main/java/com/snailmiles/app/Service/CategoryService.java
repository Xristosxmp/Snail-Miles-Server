package com.snailmiles.app.Service;


import com.snailmiles.app.DTO.*;
import com.snailmiles.app.Models.*;
import com.snailmiles.app.Repo.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategoriesWithDetails() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    private CategoryDTO mapToCategoryDTO(Category category) {
        Set<CompanyDTO> companyDTOs = category.getCompanies().stream()
                .map(this::mapToCompanyDTO)
                .collect(Collectors.toSet());
        return new CategoryDTO(category.getId(), category.getName(), category.getImageBase64() , companyDTOs);
    }

    private CompanyDTO mapToCompanyDTO(Company company) {
        Set<ChainDTO> chainDTOs = company.getChains().stream()
                .map(this::mapToChainDTO)
                .collect(Collectors.toSet());
        return new CompanyDTO(company.getId(), company.getName(), company.getImageBase64(), chainDTOs);
    }

    private ChainDTO mapToChainDTO(Chains chain) {
        Set<OfferDTO> offerDTOs = chain.getOffers().stream()
                .map(this::mapToOfferDTO)
                .collect(Collectors.toSet());
        return new ChainDTO(chain.getId(), chain.getName(), offerDTOs);
    }

    private OfferDTO mapToOfferDTO(Offer offer) {
        return new OfferDTO(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getRequiredPoints());
    }
}
