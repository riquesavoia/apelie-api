package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.exception.BadRequestResponse;
import com.apelie.apelieapi.dto.exception.GeneralExceptionResponse;
import com.apelie.apelieapi.dto.product.CreateProductDTO;
import com.apelie.apelieapi.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.dto.user.CreateUserDto;
import com.apelie.apelieapi.mappers.StoreMapper;
import com.apelie.apelieapi.services.ProductService;
import com.apelie.apelieapi.services.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all stores", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StoreResponseDTO.class))))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StoreResponseDTO> getAllStores() {
        return storeService.getAllStores()
                .stream()
                .map(StoreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get store by ID", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StoreResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreResponseDTO getStoreById(@PathVariable Long id) {
        return StoreMapper.toDto(storeService.getStoreById(id));
    }

    @Operation(summary = "Create new product", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PostMapping("/{storeId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody CreateProductDTO createProductDTO, @PathVariable Long storeId) {
        productService.createProduct(createProductDTO, storeId);
    }

    @Operation(summary = "Create new store", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateStoreDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStore(@Valid @RequestBody CreateStoreDTO createStoreDTO) {
        storeService.createStore(createStoreDTO);
    }

    @Operation(summary = "Update a store", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateStoreDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "404", description = "User doesn't have a store", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneralExceptionResponse.class)))
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStore(@Valid @RequestBody CreateStoreDTO createStoreDTO) {
        storeService.updateStore(createStoreDTO);
    }
}
