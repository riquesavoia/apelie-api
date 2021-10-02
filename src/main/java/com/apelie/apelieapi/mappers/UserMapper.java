package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.User;

public class UserMapper {
    public static UserResponseDto toDto(User user, boolean hasStore) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto(
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getCpf(),
                user.getPhotoUrl(),
                hasStore
        );

        return userResponseDto;
    }
}
