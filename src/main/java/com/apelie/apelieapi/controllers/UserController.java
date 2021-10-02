package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.controllers.dto.exception.BadRequestResponse;
import com.apelie.apelieapi.controllers.dto.user.CreateUserDto;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/users")
public interface UserController {

    @Operation(summary = "Create new user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateUserDto createUserRequest);

    @Operation(summary = "Get information about logged user", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid token")
    })
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserResponseDto getLoggedUser();
}
