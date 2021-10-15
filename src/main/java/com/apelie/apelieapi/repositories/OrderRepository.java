package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
