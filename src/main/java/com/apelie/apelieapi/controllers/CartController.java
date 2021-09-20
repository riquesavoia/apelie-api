package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.mappers.ProductMapper;
import com.apelie.apelieapi.services.CartService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getCart() {
        return cartService.getProducts(userService.getLoggedUser().getUserId())
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(@PathVariable Long productId) {
        cartService.addProduct(productId, userService.getLoggedUser().getUserId());
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductInCart(@PathVariable Long productId) {
        cartService.removeProduct(productId, userService.getLoggedUser().getUserId());
    }
}
