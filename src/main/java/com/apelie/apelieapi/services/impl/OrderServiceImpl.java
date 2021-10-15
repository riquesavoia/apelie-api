package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.order.CreateOrderDto;
import com.apelie.apelieapi.models.*;
import com.apelie.apelieapi.models.enums.OrderStatus;
import com.apelie.apelieapi.repositories.AddressRepository;
import com.apelie.apelieapi.repositories.OrderRepository;
import com.apelie.apelieapi.repositories.ProductRepository;
import com.apelie.apelieapi.services.CartService;
import com.apelie.apelieapi.services.OrderService;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.sql.Date;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> createOrder(CreateOrderDto createOrderDto, User user) {
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
            order.setCreatedAt(new Date(System.currentTimeMillis()));
            order.setItemList(new ArrayList(itemMap.get(store)));
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
    }
}
