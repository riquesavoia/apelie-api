package com.apelie.apelieapi.services;

import com.apelie.apelieapi.dto.product.CreateProductDTO;

import javax.naming.NoPermissionException;

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
}
