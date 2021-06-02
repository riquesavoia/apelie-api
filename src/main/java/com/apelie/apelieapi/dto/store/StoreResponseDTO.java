package com.apelie.apelieapi.dto.store;

import com.apelie.apelieapi.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.enums.Category;
import com.apelie.apelieapi.models.enums.PaymentMethod;
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
    private String twitterLink;
    private Category category;
    private List<PaymentMethod> paymentMethods;
    private String instagramLink;
    private String state;
    private String facebookLink;
    private String youtubeLink;
    private String bannerUrl;
    private String theme;
    private String street;
    private String city;
    private String cep;
    private String name;
    private String email;
    private String phone;
    private String addressNumber;
    private String neighbourhood;
    private Float rating;
    private String description;
    private String logoUrl;
}
