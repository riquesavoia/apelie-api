package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.cart.CreateCartItemDTO;
import com.apelie.apelieapi.controllers.dto.cart.UpdateCartItemDTO;
import com.apelie.apelieapi.models.CartItem;
import com.apelie.apelieapi.models.Product;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.repositories.CartItemRepository;
import com.apelie.apelieapi.repositories.ProductRepository;
import com.apelie.apelieapi.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public CartItem addCartItem(CreateCartItemDTO createCartItemDTO, User user) {
        try {
            Product product = productRepository
                    .findById(createCartItemDTO.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Product not found"));

            if (product.getQuantity() < 1) {
                throw new NoSuchElementException("Product out of stock");
            }

            CartItem newCartItem = new CartItem();
            List<CartItem> userCartItemList = cartItemRepository.findAllByOwnerUserId(user.getUserId());

            List<CartItem> existingCartItemList = userCartItemList
                    .stream()
                    .filter(cartItem -> cartItem.getProduct().equals(product))
                    .collect(Collectors.toList());

            int remainingProducts = product.getQuantity();
            for (CartItem currentCartItem : existingCartItemList) {
                remainingProducts -= currentCartItem.getQuantity();
            }

            if (remainingProducts <= 0) {
                throw new NoSuchElementException("Product out of stock");
            }

            newCartItem.setProduct(product);
            newCartItem.setDescription(createCartItemDTO.getDescription());
            newCartItem.setOwner(user);

            newCartItem.setQuantity(Math.min(remainingProducts, createCartItemDTO.getQuantity()));

            cartItemRepository.save(newCartItem);

            return newCartItem;
        } catch (Exception e) {
            LOGGER.error("Error when adding a cart item", e);
            throw e;
        }
    }

    @Override
    public CartItem updateCartItem(UpdateCartItemDTO updateCartItemDTO, User user) {
        try {
            CartItem updatedCartItem = cartItemRepository
                    .findById(updateCartItemDTO.getCartItemId())
                    .orElseThrow(() -> new NoSuchElementException("Cart item not found"));

            if (updatedCartItem.getOwner().getUserId() != user.getUserId()) {
                throw new AccessControlException("You don't have permission to edit this cart item");
            }

            if (updateCartItemDTO.getQuantity() == 0) {
                cartItemRepository.delete(updatedCartItem);
                return null;
            }

            List<CartItem> userCartItemList = cartItemRepository.findAllByOwnerUserId(user.getUserId());

            List<CartItem> existingCartItemList = userCartItemList
                    .stream()
                    .filter(cartItem -> cartItem.getProduct().equals(updatedCartItem.getProduct()) && cartItem.getCartItemId() != updateCartItemDTO.getCartItemId())
                    .collect(Collectors.toList());

            int remainingProducts = updatedCartItem.getProduct().getQuantity();
            for (CartItem currentCartItem : existingCartItemList) {
                remainingProducts -= currentCartItem.getQuantity();
            }

            if (remainingProducts <= 0) {
                throw new NoSuchElementException("Product out of stock");
            }

            updatedCartItem.setQuantity(Math.min(remainingProducts, updateCartItemDTO.getQuantity()));

            updatedCartItem.setDescription(updateCartItemDTO.getDescription());

            cartItemRepository.save(updatedCartItem);

            return updatedCartItem;
        } catch (Exception e) {
            LOGGER.error("Error when trying to update a cart item");
            throw e;
        }
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        try {
            List<CartItem> cartItemList = cartItemRepository.findAllByOwnerUserId(userId);

            for (CartItem cartItem:cartItemList) {
                if (cartItem.getQuantity() > cartItem.getProduct().getQuantity()) {
                    if (cartItem.getProduct().getQuantity() < 1) {
                        cartItemRepository.delete(cartItem);
                    }
                    cartItem.setQuantity(cartItem.getProduct().getQuantity());
                    cartItemRepository.save(cartItem);
                }
            }

            return cartItemList;
        } catch (Exception e) {
            LOGGER.error("Error when getting cart items");
            throw e;
        }
    }

    @Override
    public void clearCartItems(Long userId) {
        try {
            List<CartItem> cartItemList = cartItemRepository.findAllByOwnerUserId(userId);
            cartItemRepository.deleteInBatch(cartItemList);
        } catch (Exception e) {
            LOGGER.error("Error when clearing cart items");
            throw e;
        }
    }

}
