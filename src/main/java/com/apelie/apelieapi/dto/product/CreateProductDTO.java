package com.apelie.apelieapi.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductDTO {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    private float price;

    @Size(max=200, message = "Description must have at most 200 characters")
    private String description;

    @NotNull(message = "Quantity cannot be null")
    private int quantity;

    @NotNull(message = "Category cannot be null")
    private String category;

    private List<String> images;
}
