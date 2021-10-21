package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @EntityGraph(attributePaths = {"owner", "product", "product.images"})
    List<CartItem> findAllByOwnerUserId(Long userId);
}
