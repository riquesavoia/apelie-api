package com.apelie.apelieapi.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long userId;
    private String fullName;
    private String city;
    private String email;
    private String street;
    private String state;
    private String cep;
    private String addressNumber;
    private String neighbourhood;
    private String gender;
    private String cpf;
    private String birthDate;
    private String photoUrl;

    public UserResponseDto(Long userId, String fullName, String city, String email, String street, String state, String cep, String addressNumber, String neighbourhood, String gender, String cpf, String birthDate, String photoUrl) {
        this.userId = userId;
        this.fullName = fullName;
        this.city = city;
        this.email = email;
        this.street = street;
        this.state = state;
        this.cep = cep;
        this.addressNumber = addressNumber;
        this.neighbourhood = neighbourhood;
        this.gender = gender;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.photoUrl = photoUrl;
    }
}
