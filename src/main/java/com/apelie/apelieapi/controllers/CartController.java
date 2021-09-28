package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.exception.BadRequestResponse;
import com.apelie.apelieapi.dto.exception.GeneralExceptionResponse;
import com.apelie.apelieapi.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.dto.user.CreateUserDto;
import com.apelie.apelieapi.mappers.ProductMapper;
import com.apelie.apelieapi.services.CartService;
import com.apelie.apelieapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
