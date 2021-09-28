package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.ProductController;
import com.apelie.apelieapi.dto.product.CreateProductDTO;
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
}
