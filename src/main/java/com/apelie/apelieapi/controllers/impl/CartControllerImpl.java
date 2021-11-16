package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.CartController;
import com.apelie.apelieapi.controllers.dto.cart.CartItemResponseDTO;
import com.apelie.apelieapi.controllers.dto.cart.CreateCartItemDTO;
import com.apelie.apelieapi.controllers.dto.cart.UpdateCartItemDTO;
import com.apelie.apelieapi.controllers.dto.order.CreateOrderDto;
import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.mappers.CartItemMapper;
import com.apelie.apelieapi.mappers.OrderMapper;
import com.apelie.apelieapi.services.CartService;
import com.apelie.apelieapi.services.OrderService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartControllerImpl implements CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Override
    public List<CartItemResponseDTO> getCart() {
        return cartService.getCartItems(userService.getLoggedUser().getUserId())
                .stream()
                .map(CartItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemResponseDTO addCartItem(CreateCartItemDTO cartItemDTO) {
        return CartItemMapper.toDto(cartService.addCartItem(cartItemDTO, userService.getLoggedUser()));
    }

    @Override
    public CartItemResponseDTO updateCartItem(UpdateCartItemDTO cartItemDTO) {
        return CartItemMapper.toDto(cartService.updateCartItem(cartItemDTO, userService.getLoggedUser()));
    }

    @Override
    public List<OrderResponseDto> checkout(CreateOrderDto createOrderDto) {
        return orderService.createOrder(createOrderDto, userService.getLoggedUser())
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void clearCartItems() {
        cartService.clearCartItems(userService.getLoggedUser().getUserId());
    }
}
