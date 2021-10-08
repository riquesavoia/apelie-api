package com.apelie.apelieapi.controllers.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CreateCartItemDTO {
    @NotNull(message = "Product id cannot be null")
    private Long productId;

    @Size(max=200, message = "Description must have at most 200 characters")
    private String description;

    @NotNull(message = "Quantity cannot be null")
    @Min(1)
    private int quantity;
}
