package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByOwnerUserId(Long userId);
}
