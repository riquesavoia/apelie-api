package com.apelie.apelieapi.controllers.dto.product;

import com.apelie.apelieapi.models.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {

    private Long productId;
    private float price;
    private String name;
    private String description;
    private int quantity;
    private String category;
    private List<ProductImage> images;
}
