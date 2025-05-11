package com.snailmiles.app.admin.AdminControllers;

import com.snailmiles.app.Models.Category;
import com.snailmiles.app.Models.Chains;
import com.snailmiles.app.Models.Offer;
import com.snailmiles.app.admin.AdminControllers.AdminDTOS.AdminUserUpdateRequest;
import com.snailmiles.app.admin.AdminControllers.AdminDTOS.OfferCreateRequest;
import com.snailmiles.app.admin.responses.AdminUsersResponse;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import com.snailmiles.app.admin.services.categories.AdminCategoriesService;
import com.snailmiles.app.admin.services.chains.AdminChainService;
import com.snailmiles.app.admin.services.offers.AdminOffersService;
import com.snailmiles.app.admin.services.users.AdminUserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminUserService userService;
    private final AdminCategoriesService categoriesService;
    private final AdminOffersService offersService;
    private final AdminChainService chainService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminUsersResponse getUsersInfo() {
        return userService.getUsersInfo();
    }

    @DeleteMapping("/users/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PutMapping(value = "/users/{id}/update")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody AdminUserUpdateRequest req) {
        return userService.updateUser(id,req);
    }

    @PostMapping(value = "/category/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCategory(@RequestParam("name") String name,
                                            @RequestParam(value = "image", required = true) MultipartFile imageFile) {
        return categoriesService.createCategory(name,imageFile);
    }



    @GetMapping("/api/chains")
    public List<Chains> getAllChains() {
        return chainService.getAllChains();
    }

    @PostMapping("/offers/create")
    public ResponseEntity<Void> createOffer(
            @RequestParam("chain") String chain_id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("required_points") int required_points,
            @RequestParam("discount") String discount) {
        return offersService.createOffer(OfferCreateRequest.builder()
                .withChain_id(chain_id)
                .withDescription(description)
                .withTittle(title)
                .withDiscount(discount)
                .withRequired_points(required_points)
                .build());
    }




}
