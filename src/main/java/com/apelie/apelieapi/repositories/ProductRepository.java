package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByStoreStoreId(Long storeId);

}
