package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.CartController;
import com.apelie.apelieapi.controllers.dto.cart.CartItemResponseDTO;
import com.apelie.apelieapi.controllers.dto.cart.CreateCartItemDTO;
import com.apelie.apelieapi.controllers.dto.cart.UpdateCartItemDTO;
import com.apelie.apelieapi.mappers.CartItemMapper;
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
    public List<CartItemResponseDTO> getCart() {
        return cartService.getCartItems(userService.getLoggedUser().getUserId())
                .stream()
                .map(CartItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemResponseDTO addCartItem(CreateCartItemDTO cartItemDTO) {
        return CartItemMapper.toDto(cartService.addCartItem(cartItemDTO, userService.getLoggedUser()));
    }

    @Override
    public CartItemResponseDTO updateCartItem(UpdateCartItemDTO cartItemDTO) {
        return CartItemMapper.toDto(cartService.updateCartItem(cartItemDTO, userService.getLoggedUser()));
    }
}
