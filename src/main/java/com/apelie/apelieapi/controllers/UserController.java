package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.controllers.dto.address.AddressResponseDto;
import com.apelie.apelieapi.controllers.dto.address.CreateAddressDto;
import com.apelie.apelieapi.controllers.dto.address.UpdateAddressDto;
import com.apelie.apelieapi.controllers.dto.exception.BadRequestResponse;
import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.controllers.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.controllers.dto.user.CreateUserDto;
import com.apelie.apelieapi.controllers.dto.user.UpdateUserDto;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/users")
public interface UserController {

    @Operation(summary = "Create new user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateUserDto createUserRequest);

    @Operation(summary = "Update logged user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@Valid @RequestBody UpdateUserDto updateUserDto);

    @Operation(summary = "Get information about logged user", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserResponseDto getLoggedUser();

    @Operation(summary = "Get logged user store", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StoreResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @GetMapping("/me/store")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StoreResponseDTO getLoggedUserStore();

    @Operation(summary = "Create a new address for logged user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addresses")
    @ResponseBody
    public AddressResponseDto createAddress(@Valid @RequestBody CreateAddressDto createAddressDto);

    @Operation(summary = "Update a given address", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/addresses")
    @ResponseBody
    public AddressResponseDto updateAddress(@Valid @RequestBody UpdateAddressDto updateAddressDto);

    @Operation(summary = "Delete a given address", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @DeleteMapping("/addresses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id);

    @Operation(summary = "Get all logged user addresses", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @GetMapping("/addresses")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AddressResponseDto> getAllAddresses();

    @Operation(summary = "Get all logged user orders", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<OrderResponseDto> getAllUserOrders();
}
