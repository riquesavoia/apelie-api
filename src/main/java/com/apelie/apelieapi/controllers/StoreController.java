package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.controllers.dto.exception.BadRequestResponse;
import com.apelie.apelieapi.controllers.dto.exception.GeneralExceptionResponse;
import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.controllers.dto.product.CreateProductDTO;
import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.controllers.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.controllers.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.controllers.dto.store_review.StoreReviewResponseDto;
import com.apelie.apelieapi.models.enums.StoreCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/stores")
public interface StoreController {

    @Operation(summary = "Get all stores", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StoreResponseDTO.class))))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StoreResponseDTO> getAllStores(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float rating,
            @RequestParam(required = false) List<StoreCategory> categories);

    @Operation(summary = "Get all store categories", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public StoreCategory[] getAllStoreCategories();

    @Operation(summary = "Get store by ID", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StoreResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreResponseDTO getStoreById(@PathVariable Long id);

    @Operation(summary = "Create new product", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PostMapping("/{storeId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody CreateProductDTO createProductDTO, @PathVariable Long storeId);

    @Operation(summary = "Create new store", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateStoreDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStore(@Valid @RequestBody CreateStoreDTO createStoreDTO);

    @Operation(summary = "Update a store", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateStoreDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "User doesn't have a store", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStore(@Valid @RequestBody CreateStoreDTO createStoreDTO);

    @Operation(summary = "Delete store by ID", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StoreResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable Long id);

    @Operation(summary = "Get products in store by id", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{storeId}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getProductsInStore(@PathVariable Long storeId);

    @Operation(summary = "Get all orders by store id", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{storeId}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> getAllStoreOrders(@PathVariable Long storeId);

    @Operation(summary = "Get all reviews by store id", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StoreReviewResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{storeId}/reviews")
    @ResponseStatus(HttpStatus.OK)
    public List<StoreReviewResponseDto> getAllStoreReviews(@PathVariable Long storeId);

    @Operation(summary = "Insert order tracking code", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Store/order not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @PatchMapping("/{storeId}/orders/{orderId}/tracking-code/{trackingCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putOrderTrackingCode(@PathVariable String trackingCode, @PathVariable Long storeId, @PathVariable Long orderId);
}
