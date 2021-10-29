package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.ProductController;
import com.apelie.apelieapi.controllers.dto.product.CreateProductDTO;
import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.mappers.ProductMapper;
import com.apelie.apelieapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public void deleteProduct(Long productId)  {
        productService.deleteProduct(productId);
    }

    @Override
    public void updateProduct(CreateProductDTO createProductDTO, Long productId) {
        // productService.updateProduct(createProductDTO, productId);
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        return ProductMapper.toDto(productService.getProductById(productId));
    }

    @Override
    public void deleteProductImage(Long productId, Long imageId) {

    }
}
