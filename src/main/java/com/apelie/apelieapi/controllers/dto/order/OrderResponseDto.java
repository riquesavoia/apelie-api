package com.apelie.apelieapi.controllers.dto.order;

import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.controllers.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.OrderItem;
import com.apelie.apelieapi.models.enums.OrderStatus;
import com.apelie.apelieapi.models.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

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
    private StoreResponseDTO store;
    private List<OrderItemResponseDto> itemList;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderItemResponseDto {
        private ProductResponseDTO product;
        private String description;
        private int quantity;
    }
}
