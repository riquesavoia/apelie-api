package com.apelie.apelieapi.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Name must not be null")
    @Size(max=50, message = "Name must have at most 50 characters")
    private String fullName;

    @NotBlank(message = "Password must not be null")
    @Size(min=6, message = "Password must have at least 6 characters")
    @Size(max=255, message = "Password must have at most 255 characters")
    private String password;

    @Email(message = "Email is invalid")
    @NotBlank(message = "Email must not be null")
    private String email;

    @NotBlank(message = "Gender must not be null")
    @Size(max=50, message = "Gender must have at most 255 characters")
    private String gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
}
