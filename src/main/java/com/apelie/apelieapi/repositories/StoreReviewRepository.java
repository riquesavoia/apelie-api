package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {

    List<StoreReview> findAllByStoreStoreId(Long storeId);
}
