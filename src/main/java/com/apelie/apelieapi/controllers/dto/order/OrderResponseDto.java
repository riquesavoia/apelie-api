package com.apelie.apelieapi.controllers.dto.order;

import com.apelie.apelieapi.controllers.dto.address.AddressResponseDto;
import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.controllers.dto.store_review.StoreReviewResponseDto;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.enums.OrderStatus;
import com.apelie.apelieapi.models.enums.PaymentMethod;
import com.apelie.apelieapi.models.enums.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private float totalValue;
    private UserResponseDto user;
    private OrderResponseDto.StoreResponseDto store;
    private List<OrderItemResponseDto> itemList;
    private StoreReviewResponseDto review;
    private AddressResponseDto address;

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
        private String zipCode;
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
