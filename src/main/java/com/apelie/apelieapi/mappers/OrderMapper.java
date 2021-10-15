package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.models.Order;
import com.apelie.apelieapi.models.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderResponseDto toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto(
                order.getOrderId(),
                order.getTrackingCode(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getCreatedAt(),
                UserMapper.toDto(order.getUser(), false),
                StoreMapper.toDto(order.getStore()),
                order.getItemList().stream().map(OrderMapper::toDto).collect(Collectors.toList())
        );

        return orderResponseDto;
    }

    public static OrderResponseDto.OrderItemResponseDto toDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return new OrderResponseDto.OrderItemResponseDto(
                ProductMapper.toDto(orderItem.getProduct()),
                orderItem.getDescription(),
                orderItem.getQuantity()
        );
    }
}
