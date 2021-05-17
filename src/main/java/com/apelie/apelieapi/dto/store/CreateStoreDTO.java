package com.apelie.apelieapi.dto.store;

import com.apelie.apelieapi.models.enums.Category;
import com.apelie.apelieapi.models.enums.PaymentMethod;

import java.util.List;

public class CreateStoreDTO {
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
}
