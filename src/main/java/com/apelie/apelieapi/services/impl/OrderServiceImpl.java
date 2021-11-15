package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.order.CreateOrderDto;
import com.apelie.apelieapi.models.*;
import com.apelie.apelieapi.models.enums.OrderStatus;
import com.apelie.apelieapi.repositories.*;
import com.apelie.apelieapi.services.CartService;
import com.apelie.apelieapi.services.OrderService;
import com.apelie.apelieapi.services.UserService;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserService userService;

    private static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> createOrder(CreateOrderDto createOrderDto, User user) {
        try {
            List<CartItem> cartItemList = cartService.getCartItems(user.getUserId());
            Address address = addressRepository.findById(createOrderDto.getAddressId())
                    .orElseThrow(() -> new NoSuchElementException("Invalid address"));

            if (address.getUser().getUserId() != user.getUserId()) {
                throw new AccessControlException("Invalid address");
            }

            if (cartItemList.size() <= 0) {
                throw new NoSuchElementException("You have no items in your cart");
            }

            MultiValuedMap<Store, OrderItem> itemMap = new ArrayListValuedHashMap<>();
            Map<Product, Integer> productQuantity = new HashMap<>();
            float totalValue = 0;

            List<Order> orderList = new ArrayList<>();

            for(CartItem cartItem:cartItemList) {
                OrderItem orderItem = new OrderItem();
                orderItem.setDescription(cartItem.getDescription());
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());

                itemMap.put(cartItem.getProduct().getStore(), orderItem);
                Product product = cartItem.getProduct();
                Integer quantity = productQuantity.containsKey(product) ? productQuantity.get(product) : 0;
                quantity += orderItem.getQuantity();
                productQuantity.put(product, quantity);
            }

            for(Store store: itemMap.keys()) {
                Order order = new Order();
                order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
                Set<OrderItem> itemList = new HashSet(itemMap.get(store));
                order.setItemList(itemList);
                order.setTotalValue(calculateTotalPrice(itemList));
                order.setStatus(OrderStatus.AWAITING_PAYMENT);
                order.setStore(store);
                order.setUser(user);
                order.setPaymentMethod(createOrderDto.getPaymentMethod());
                order.setAddress(address);
                orderRepository.save(order);
                orderList.add(order);
            }

            for (Product product: productQuantity.keySet()) {
                product.setQuantity(product.getQuantity() - productQuantity.get(product));
                productRepository.save(product);
            }

            cartService.clearCartItems(user.getUserId());

            return orderList;
        } catch (Exception e) {
            LOGGER.error("Error when creating a new order", e);
            throw e;
        }
    }

    @Override
    public List<Order> getAllOrdersByStore(Long storeId) {
        try {
            Store store = storeRepository
                    .findById(storeId)
                    .orElseThrow(() -> new NoSuchElementException("Store not found"));

            if (store.getOwner().getUserId() != userService.getLoggedUser().getUserId()) {
                throw new AccessControlException("You don't have permission to view orders from this store");
            }

            return orderRepository.findAllByStoreStoreId(storeId);
        } catch (Exception e) {
            LOGGER.error("Error when getting orders from store", e);
            throw e;
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(Long userId) {
        try {
            User user = userRepository
                    .findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));

            if (user.getUserId() != userService.getLoggedUser().getUserId()) {
                throw new AccessControlException("You don't have permission to view orders from this user");
            }

            return orderRepository.findAllByUserUserId(user.getUserId());
        } catch (Exception e) {
            LOGGER.error("Error when getting orders from user", e);
            throw e;
        }
    }

    @Override
    public void putOrderTrackingCode(Long storeId, Long orderId, String trackingCode) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new NoSuchElementException("Store not found"));

        if (userService.getLoggedUser().getUserId() != store.getOwner().getUserId()) {
            throw new AccessControlException("You don't have permission to edit this order");
        }

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("Order not found"));
        if (order.getStore() != store) {
            throw new NoSuchElementException("Order does not belong to this store");
        }

        order.setTrackingCode(trackingCode);
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        try {
            Order order = orderRepository
                    .findById(orderId)
                    .orElseThrow(() -> new NoSuchElementException("Order not found"));

            if (order.getUser().getUserId() != userService.getLoggedUser().getUserId()) {
                throw new AccessControlException("You don't have permission to view this order");
            }

            return order;
        } catch (Exception e) {
            LOGGER.error("Error when getting order by id", e);
            throw e;
        }
    }

    @Override
    public void insertStoreReview(StoreReview storeReview, Long orderId) {
        try {
            Order order = orderRepository
                    .findById(orderId)
                    .orElseThrow(() -> new NoSuchElementException("Order not found"));

            order.setStoreReview(storeReview);

            orderRepository.save(order);
        } catch (Exception e) {
            LOGGER.error("Error when inserting order review", e);
            throw e;
        }
    }

    /**
     * Calculates total price of an order item collection
     * @param orderItemSet
     * @return
     */
    private float calculateTotalPrice(Set<OrderItem> orderItemSet) {
        float total = 0;
        for(OrderItem orderItem:orderItemSet) {
            total += orderItem.getQuantity() * orderItem.getProduct().getPrice();
        }
        return total;
    }
}
