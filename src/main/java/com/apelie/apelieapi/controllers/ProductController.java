package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.product.CreateProductDTO;
import com.apelie.apelieapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@Valid @RequestBody CreateProductDTO createProductDTO, @PathVariable Long productId) {
        // productService.updateProduct(createProductDTO, productId);
    }
}
