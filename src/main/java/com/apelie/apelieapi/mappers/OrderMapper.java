package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.models.Order;
import com.apelie.apelieapi.models.OrderItem;
import com.apelie.apelieapi.models.Store;

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
                order.getTotalValue(),
                UserMapper.toDto(order.getUser(), false),
                OrderMapper.toDto(order.getStore()),
                order.getItemList().stream().map(OrderMapper::toDto).collect(Collectors.toList()),
                StoreReviewMapper.toDto(order.getStoreReview()),
                AddressMapper.toDto(order.getAddress())
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

    public static OrderResponseDto.StoreResponseDto toDto(Store store) {
        if (store == null) {
            return null;
        }

        return new OrderResponseDto.StoreResponseDto(
                store.getStoreId(),
                UserMapper.toDto(store.getOwner(), true),
                store.getTwitterAccount(),
                store.getCategoryList(),
                store.getInstagramAccount(),
                store.getState(),
                store.getFacebookAccount(),
                store.getYoutubeAccount(),
                store.getBannerUrl(),
                store.getPrimaryColor(),
                store.getSecondaryColor(),
                store.getStreet(),
                store.getCity(),
                store.getZipCode(),
                store.getName(),
                store.getEmail(),
                store.getPhone(),
                store.getAddressNumber(),
                store.getNeighbourhood(),
                store.getRating(),
                store.getLogoUrl(),
                store.getDescription()
        );
    }
}
