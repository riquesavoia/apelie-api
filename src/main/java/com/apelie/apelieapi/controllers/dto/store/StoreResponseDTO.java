package com.apelie.apelieapi.controllers.dto.store;

import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.enums.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class StoreResponseDTO {
    private Long storeId;
    private UserResponseDto owner;
    private String twitterAccount;
    private Set<StoreCategory> category;
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


