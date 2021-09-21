package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.exception.BadRequestResponse;
import com.apelie.apelieapi.dto.exception.GeneralExceptionResponse;
import com.apelie.apelieapi.dto.product.CreateProductDTO;
import com.apelie.apelieapi.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Delete a product", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateStoreDTO.class))),
            @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
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
