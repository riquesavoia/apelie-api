package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.product.CreateProductDTO;
import com.apelie.apelieapi.models.Product;

import java.util.List;

public interface ProductService {
    /**
     * Creates a new product in a given store
     *
     * @param createProductDTO
     * @param storeId
     */
    void createProduct(CreateProductDTO createProductDTO, Long storeId);

    /**
     * Deletes a given product
     *
     * @param productId
     */
    void deleteProduct(Long productId);

    /**
     * Gets all products from given store
     * @param storeId
     * @return
     */
    List<Product> getAllProductsByStore(Long storeId);

    /**
     * Gets a product by its id
     *
     * @param productId
     * @return
     */
    Product getProductById(Long productId);
}
