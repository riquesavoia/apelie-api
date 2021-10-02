package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.CartController;
import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.mappers.ProductMapper;
import com.apelie.apelieapi.services.CartService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartControllerImpl implements CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Override
    public List<ProductResponseDTO> getCart()  {
        return cartService.getProducts(userService.getLoggedUser().getUserId())
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addProductInCart(Long productId) {
        cartService.addProduct(productId, userService.getLoggedUser().getUserId());
    }

    @Override
    public void removeProductInCart(Long productId) {
        cartService.removeProduct(productId, userService.getLoggedUser().getUserId());
    }
}
