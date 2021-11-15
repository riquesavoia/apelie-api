package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.store_review.CreateStoreReviewDto;
import com.apelie.apelieapi.mappers.StoreReviewMapper;
import com.apelie.apelieapi.models.Order;
import com.apelie.apelieapi.models.StoreReview;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.models.enums.OrderStatus;
import com.apelie.apelieapi.repositories.OrderRepository;
import com.apelie.apelieapi.repositories.StoreRepository;
import com.apelie.apelieapi.repositories.StoreReviewRepository;
import com.apelie.apelieapi.services.OrderService;
import com.apelie.apelieapi.services.StoreReviewService;
import com.apelie.apelieapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StoreReviewServiceImpl implements StoreReviewService {

    @Autowired
    private StoreReviewRepository storeReviewRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private static Logger LOGGER = LoggerFactory.getLogger(StoreReviewServiceImpl.class);

    @Override
    public void createStoreReview(CreateStoreReviewDto createStoreReviewDto, Long orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new NoSuchElementException("Order not found"));

            User user = userService.getLoggedUser();

            if (order.getStoreReview() != null) {
                throw new AccessControlException("This order already has a review");
            }

            if (order.getUser().getUserId() != user.getUserId()) {
                throw new AccessControlException("This order does not belong to you");
            }

            if (order.getStatus() != OrderStatus.FINISHED) {
                throw new AccessControlException("You cannot review a order that is not finished");
            }

            StoreReview storeReview = StoreReviewMapper.toEntity(createStoreReviewDto);
            storeReview.setUser(user);
            storeReview.setStore(order.getStore());
            storeReviewRepository.save(storeReview);

            storeRepository.updateStoreRating(order.getStore().getStoreId());
            orderService.insertStoreReview(storeReview, order.getOrderId());
        } catch (Exception e) {
            LOGGER.error("Error on creating store review", e);
            throw e;
        }
    }

    @Override
    public List<StoreReview> getAllStoreReviewByStore(Long storeId) {
        try {
            return storeReviewRepository.findAllByStoreStoreId(storeId);
        } catch (Exception e) {
            LOGGER.error("Error on getting reviews by store", e);
            throw e;
        }
    }
}
