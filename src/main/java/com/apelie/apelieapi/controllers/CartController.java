package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.controllers.dto.cart.CartItemResponseDTO;
import com.apelie.apelieapi.controllers.dto.cart.CreateCartItemDTO;
import com.apelie.apelieapi.controllers.dto.cart.UpdateCartItemDTO;
import com.apelie.apelieapi.controllers.dto.exception.GeneralExceptionResponse;
import com.apelie.apelieapi.controllers.dto.order.CreateOrderDto;
import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/cart")
public interface CartController {

    @Operation(summary = "Get all cart items from logged user cart", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartItemResponseDTO> getCart();

    @Operation(summary = "Add a cart item into logged user cart", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateCartItemDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product out of stock", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponseDTO addCartItem(@Valid @RequestBody CreateCartItemDTO cartItemDTO);

    @Operation(summary = "Edit a cart item from logged user cart", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateCartItemDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CartItemResponseDTO updateCartItem(@Valid @RequestBody UpdateCartItemDTO cartItemDTO);

    @Operation(summary = "Checkout cart items and creates orders", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Product out of stock", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderResponseDto> checkout(@Valid @RequestBody CreateOrderDto createOrderDto);

    @Operation(summary = "Clears logged user cart items", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderDto.class)))
    })
    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCartItems();
}
