package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.store_review.CreateStoreReviewDto;
import com.apelie.apelieapi.models.StoreReview;

import java.util.List;

public interface StoreReviewService {

    /**
     * Creates a new store review
     *
     * @param createStoreReviewDto
     */
    void createStoreReview(CreateStoreReviewDto createStoreReviewDto, Long orderId);

    /**
     * Gets all store reviews by store id
     *
     * @param storeId
     * @return
     */
    List<StoreReview> getAllStoreReviewByStore(Long storeId);
}
