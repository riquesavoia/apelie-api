package com.apelie.apelieapi.services;

import com.apelie.apelieapi.models.Product;

import java.util.List;

public interface CartService {
    /**
     * Adds a new product into logged user cart
     *
     * @param productId
     * @param userId
     */
    void addProduct(Long productId, Long userId);

    /**
     * Removes a product from logged user cart
     *
     * @param productId
     * @param userId
     */
    void removeProduct(Long productId, Long userId);

    /**
     * Gets all products from logged user cart
     *
     * @param userId
     * @return
     */
    List<Product> getProducts(Long userId);
}
