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
public class UpdateCartItemDTO {
    @NotNull(message = "Cart item id cannot be null")
    private Long cartItemId;

    @Size(max=200, message = "Description must have at most 200 characters")
    private String description;

    @NotNull(message = "Quantity cannot be null")
    @Min(0)
    private int quantity;
}
