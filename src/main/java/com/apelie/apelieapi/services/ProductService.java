package com.apelie.apelieapi.services;

import com.apelie.apelieapi.dto.product.CreateProductDTO;
import com.apelie.apelieapi.models.Product;

import javax.naming.NoPermissionException;
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
}
