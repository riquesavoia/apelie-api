package com.apelie.apelieapi.controllers.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String fullName;
    private String email;
    private String photoUrl;
    private boolean hasStore;
}
