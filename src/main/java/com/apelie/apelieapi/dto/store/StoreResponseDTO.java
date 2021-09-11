package com.apelie.apelieapi.dto.store;

import com.apelie.apelieapi.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.Product;
import com.apelie.apelieapi.models.ProductImage;
import com.apelie.apelieapi.models.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StoreResponseDTO {
    private Long storeId;
    private UserResponseDto owner;
    private String twitterAccount;
    private List<Category> category;
    private List<ProductResponseDTO> products;
    private String instagramAccount;
    private String state;
    private String facebookAccount;
    private String youtubeAccount;
    private String bannerUrl;
    private String primaryColor;
    private String secondaryColor;
    private String street;
    private String city;
    private String cep;
    private String name;
    private String email;
    private String phone;
    private String addressNumber;
    private String neighbourhood;
    private Float rating;
    private String logoUrl;
    private String description;
}
