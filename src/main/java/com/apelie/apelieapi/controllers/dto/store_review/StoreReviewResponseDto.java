package com.apelie.apelieapi.controllers.dto.store_review;

import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StoreReviewResponseDto {

    private UserResponseDto user;
    private String description;
    private float rating;
}
