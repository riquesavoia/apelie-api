package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.dto.product.CreateProductDTO;
import com.apelie.apelieapi.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.models.Product;

public class ProductMapper {
    public static Product toEntity(CreateProductDTO createProductDTO) {
        if (createProductDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setDescription(createProductDTO.getDescription());
        product.setCategory(createProductDTO.getCategory());
        product.setName(createProductDTO.getName());
        product.setPrice(createProductDTO.getPrice());
        product.setQuantity(createProductDTO.getQuantity());

        return product;
    };

    public static ProductResponseDTO toDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponseDTO(
                product.getProductId(),
                product.getPrice(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getCategory(),
                product.getImages());
    }
}
