package com.apelie.apelieapi.controllers.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressResponseDto {
    private Long addressId;
    private String city;
    private String complement;
    private String district;
    private String number;
    private String state;
    private String street;
    private String zipCode;
}
