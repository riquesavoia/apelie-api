package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.controllers.dto.store_review.CreateStoreReviewDto;
import com.apelie.apelieapi.controllers.dto.store_review.StoreReviewResponseDto;
import com.apelie.apelieapi.models.StoreReview;

public class StoreReviewMapper {

    public static StoreReview toEntity(CreateStoreReviewDto createStoreReviewDto) {
        if (createStoreReviewDto == null) {
            return null;
        }

        StoreReview storeReview = new StoreReview();
        storeReview.setDescription(createStoreReviewDto.getDescription());
        storeReview.setRating(createStoreReviewDto.getRating());

        return storeReview;
    }

    public static StoreReviewResponseDto toDto(StoreReview storeReview) {
        if (storeReview == null) {
            return null;
        }

        return new StoreReviewResponseDto(
                UserMapper.toDto(storeReview.getUser(), false),
                storeReview.getDescription(),
                storeReview.getRating()
        );
    }
}
