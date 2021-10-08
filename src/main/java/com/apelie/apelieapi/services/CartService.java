package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.cart.CreateCartItemDTO;
import com.apelie.apelieapi.controllers.dto.cart.UpdateCartItemDTO;
import com.apelie.apelieapi.models.CartItem;
import com.apelie.apelieapi.models.Product;
import com.apelie.apelieapi.models.User;

import java.util.List;

public interface CartService {

    /**
     * Adds a new cart item into given user cart
     *
     * @param createCartItemDTO
     * @param user
     * @return
     */
    CartItem addCartItem(CreateCartItemDTO createCartItemDTO, User user);

    /**
     * Updates an existing cart item from user cart
     *
     * @param updateCartItemDTO
     * @param user
     * @return
     */
    CartItem updateCartItem(UpdateCartItemDTO updateCartItemDTO, User user);

    /**
     * Gets all cart items from given user
     *
     * @param userId
     * @return
     */
    List<CartItem> getCartItems(Long userId);
}
