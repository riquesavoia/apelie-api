package com.apelie.apelieapi.controllers.dto.order;

import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.controllers.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.OrderItem;
import com.apelie.apelieapi.models.enums.OrderStatus;
import com.apelie.apelieapi.models.enums.PaymentMethod;
import com.apelie.apelieapi.models.enums.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private String trackingCode;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private Date createdAt;
    private UserResponseDto user;
    private OrderResponseDto.StoreResponseDto store;
    private List<OrderItemResponseDto> itemList;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderItemResponseDto {
        private ProductResponseDTO product;
        private String description;
        private int quantity;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class StoreResponseDto {
        private Long storeId;
        private UserResponseDto owner;
        private String twitterAccount;
        private Set<StoreCategory> category;
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
}
