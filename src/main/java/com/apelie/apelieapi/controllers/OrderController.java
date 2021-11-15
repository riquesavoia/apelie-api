package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.controllers.dto.exception.BadRequestResponse;
import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.controllers.dto.store_review.CreateStoreReviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/orders")
public interface OrderController {

    @Operation(summary = "Get order by id", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto getOrderById(@PathVariable Long orderId);

    @Operation(summary = "Create store review", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateStoreReviewDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
    })
    @PostMapping("/{orderId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStoreReview(@Valid @RequestBody CreateStoreReviewDto createStoreReviewDto, @PathVariable Long orderId);
}
