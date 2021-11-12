package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.store.storeId = ?1 and p.deleted = false")
    List<Product> findAllByStoreStoreId(Long storeId);

}
