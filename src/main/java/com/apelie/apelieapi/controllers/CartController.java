package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.controllers.dto.exception.GeneralExceptionResponse;
import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/cart")
public interface CartController {

    @Operation(summary = "Gets all products from logged user cart", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getCart();

    @Operation(summary = "Adds a product into logged user cart", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product out of stock", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(@PathVariable Long productId);

    @Operation(summary = "Remove a product from logged user cart", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductInCart(@PathVariable Long productId);
}
