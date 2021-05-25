package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.dto.user.UserResponseDto;
import com.apelie.apelieapi.mappers.StoreMapper;
import com.apelie.apelieapi.services.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

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
}
