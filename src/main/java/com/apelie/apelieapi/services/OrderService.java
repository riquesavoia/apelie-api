package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.order.CreateOrderDto;
import com.apelie.apelieapi.models.Order;
import com.apelie.apelieapi.models.StoreReview;
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

    /**
     * Gets all orders from store by its id
     *
     * @param storeId
     * @return
     */
    List<Order> getAllOrdersByStore(Long storeId);

    /**
     * Gets all orders from usser by its id
     *
     * @param userId
     * @return
     */
    List<Order> getAllOrdersByUser(Long userId);

    /**
     * Puts order tracking code
     *
     * @param storeId
     * @param orderId
     * @param trackingCode
     */
    void putOrderTrackingCode(Long storeId, Long orderId, String trackingCode);

    /**
     * Gets order by id
     *
     * @param orderId
     * @return
     */
    Order getOrderById(Long orderId);

    /**
     * Inserts a store review in given order
     *
     * @param storeReview
     */
    void insertStoreReview(StoreReview storeReview, Long orderId);
}
