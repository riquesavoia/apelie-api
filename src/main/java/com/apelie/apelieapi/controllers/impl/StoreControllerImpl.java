package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.StoreController;
import com.apelie.apelieapi.controllers.dto.order.OrderResponseDto;
import com.apelie.apelieapi.controllers.dto.product.CreateProductDTO;
import com.apelie.apelieapi.controllers.dto.product.ProductResponseDTO;
import com.apelie.apelieapi.controllers.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.controllers.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.mappers.OrderMapper;
import com.apelie.apelieapi.mappers.ProductMapper;
import com.apelie.apelieapi.mappers.StoreMapper;
import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.enums.StoreCategory;
import com.apelie.apelieapi.services.OrderService;
import com.apelie.apelieapi.services.ProductService;
import com.apelie.apelieapi.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.apelie.apelieapi.models.specifications.StoreSpecifications.*;

@RestController
public class StoreControllerImpl implements StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public List<StoreResponseDTO> getAllStores(String name, Float rating, List<StoreCategory> categories, Long ownerId) {
        Specification<Store> specifications =
                Specification.where(isNameLike(name))
                        .and(isRatingGreaterThan(rating))
                        .and(belongsToCategories(categories))
                        .and(belongsToUser(ownerId));

        return storeService.getAllStores(specifications)
                .stream()
                .map(StoreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StoreCategory[] getAllStoreCategories() {
        return StoreCategory.class.getEnumConstants();
    }

    @Override
    public StoreResponseDTO getStoreById(Long id) {
        return StoreMapper.toDto(storeService.getStoreById(id));
    }

    @Override
    public void createProduct(CreateProductDTO createProductDTO, Long storeId) {
        productService.createProduct(createProductDTO, storeId);
    }

    @Override
    public void createStore(CreateStoreDTO createStoreDTO) {
        storeService.createStore(createStoreDTO);
    }

    @Override
    public void updateStore(CreateStoreDTO createStoreDTO) {
        storeService.updateStore(createStoreDTO);
    }

    @Override
    public List<ProductResponseDTO> getProductsInStore(Long id) {
        return productService.getAllProductsByStore(id)
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllStoreOrders(Long storeId) {
        return orderService.getAllOrdersByStore(storeId)
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }
}
