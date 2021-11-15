package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"user", "store", "store.categoryList", "itemList", "itemList.product", "itemList.product.images"})
    List<Order> findAllByStoreStoreId(Long storeId);

    @EntityGraph(attributePaths = {"user", "store", "store.categoryList", "itemList", "itemList.product", "itemList.product.images"})
    List<Order> findAllByUserUserId(Long userId);

    @EntityGraph(attributePaths = {"user", "store", "store.categoryList", "itemList", "itemList.product", "itemList.product.images"})
    Optional<Order> findById(Long orderId);
}
