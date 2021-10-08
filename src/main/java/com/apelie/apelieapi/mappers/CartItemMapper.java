package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.controllers.dto.cart.CartItemResponseDTO;
import com.apelie.apelieapi.models.CartItem;

public class CartItemMapper {
    public static CartItemResponseDTO toDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO(
                cartItem.getCartItemId(),
                ProductMapper.toDto(cartItem.getProduct()),
                cartItem.getDescription(),
                cartItem.getQuantity());

        return cartItemResponseDTO;
    }
}
