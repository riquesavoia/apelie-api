package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.dto.user.UserResponseDto;
import com.apelie.apelieapi.models.User;

public class UserMapper {
    public static UserResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto(
                user.getUserId(),
                user.getFullName(),
                user.getCity(),
                user.getEmail(),
                user.getStreet(),
                user.getState(),
                user.getCep(),
                user.getAddressNumber(),
                user.getNeighbourhood(),
                user.getGender(),
                user.getCpf(),
                user.getBirthDate() != null ? user.getBirthDate().toString() : null,
                user.getPhotoUrl()
        );

        return userResponseDto;
    }
}
