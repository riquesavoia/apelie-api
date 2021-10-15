package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.order.CreateOrderDto;
import com.apelie.apelieapi.models.Order;
import com.apelie.apelieapi.models.User;

import java.util.List;

public interface OrderService {

    /**
     * Creates one or more orders based on logged user cart items
     *
     * @param createOrderDto
     * @param user
     * @return
     */
    List<Order> createOrder(CreateOrderDto createOrderDto, User user);
}
