package com.apelie.apelieapi.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank
    @Size(max=50)
    private String fullName;

    @NotBlank
    @Size(min=6)
    @Size(max=255)
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max=50)
    private String gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
}
