package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.OrderController;
import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.mappers.OrderMapper;
import com.apelie.apelieapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        return OrderMapper.toDto(orderService.getOrderById(orderId));
    }
}
