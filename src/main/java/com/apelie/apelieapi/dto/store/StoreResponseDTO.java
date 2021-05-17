package com.apelie.apelieapi.dto.store;

import com.apelie.apelieapi.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.enums.Category;
import com.apelie.apelieapi.models.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

    public StoreResponseDTO(Long storeId, UserResponseDto owner, String twitterLink, Category category, List<PaymentMethod> paymentMethods, String instagramLink, String state, String facebookLink, String youtubeLink, String bannerUrl, String theme, String street, String city, String cep, String name, String email, String phone, String addressNumber, String neighbourhood, Float rating) {
        this.storeId = storeId;
        this.owner = owner;
        this.twitterLink = twitterLink;
        this.category = category;
        this.paymentMethods = paymentMethods;
        this.instagramLink = instagramLink;
        this.state = state;
        this.facebookLink = facebookLink;
        this.youtubeLink = youtubeLink;
        this.bannerUrl = bannerUrl;
        this.theme = theme;
        this.street = street;
        this.city = city;
        this.cep = cep;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addressNumber = addressNumber;
        this.neighbourhood = neighbourhood;
        this.rating = rating;
    }
}
