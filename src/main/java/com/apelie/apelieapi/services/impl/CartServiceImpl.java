package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.models.Cart;
import com.apelie.apelieapi.models.Product;
import com.apelie.apelieapi.repositories.CartRepository;
import com.apelie.apelieapi.repositories.ProductRepository;
import com.apelie.apelieapi.services.CartService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Long productId, Long userId) {
        Cart cart = cartRepository.findByOwnerUserId(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setOwner(userService.getLoggedUser());
            cart.setProductList(new ArrayList<>());
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));

        if (product.getQuantity() <= 0) {
            throw new NoSuchElementException("Product out of stock");
        }

        cart.getProductList().add(product);
        cartRepository.save(cart);
    }

    @Override
    public void removeProduct(Long productId, Long userId) {
        Cart cart = cartRepository.findByOwnerUserId(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setOwner(userService.getLoggedUser());
            cart.setProductList(new ArrayList<>());
            cartRepository.save(cart);
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));

        cart.getProductList().remove(product);
        cartRepository.save(cart);
    }

    @Override
    public List<Product> getProducts(Long userId) {
        Cart cart = cartRepository.findByOwnerUserId(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setOwner(userService.getLoggedUser());
            cart.setProductList(new ArrayList<>());
            cartRepository.save(cart);
        }

        return cart.getProductList();
    }
}
