package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByOwnerUserId(Long userId);
}
