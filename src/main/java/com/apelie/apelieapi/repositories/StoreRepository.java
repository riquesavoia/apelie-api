package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
