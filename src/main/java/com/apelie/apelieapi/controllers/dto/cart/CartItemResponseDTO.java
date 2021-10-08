package com.apelie.apelieapi.controllers.dto.cart;

import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemResponseDTO {
    private Long id;
    private ProductResponseDTO product;
    private String description;
    private int quantity;
}
