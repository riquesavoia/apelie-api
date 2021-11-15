package com.apelie.apelieapi.controllers.dto.store_review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CreateStoreReviewDto {

    @Size(max=200, message = "Description must have at most 15 characters")
    private String description;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private float rating;
}
