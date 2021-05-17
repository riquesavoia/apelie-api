package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
