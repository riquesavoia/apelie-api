package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStoreStoreId(Long storeId);

    List<Order> findAllByUserUserId(Long userId);
}
