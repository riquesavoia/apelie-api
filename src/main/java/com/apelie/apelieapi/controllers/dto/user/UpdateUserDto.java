package com.apelie.apelieapi.controllers.dto.user;

import com.apelie.apelieapi.controllers.validators.EncodedImageFileConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDto {
    @NotBlank(message = "Name must not be null")
    @Size(max=50, message = "Name must have at most 50 characters")
    private String fullName;

    @Size(min=6, message = "Password must have at least 6 characters")
    @Size(max=255, message = "Password must have at most 255 characters")
    private String password;

    @Email(message = "Email is invalid")
    @NotBlank(message = "Email must not be null")
    private String email;

    @EncodedImageFileConstraint
    private String photo;
}
