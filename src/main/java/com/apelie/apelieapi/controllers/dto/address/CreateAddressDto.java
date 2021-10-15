package com.apelie.apelieapi.controllers.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CreateAddressDto {
    @NotEmpty
    @Size(max=50, message = "City must have at most 50 characters")
    private String city;

    @Size(max=50, message = "Complement must have at most 50 characters")
    private String complement;

    @Size(max=50, message = "District must have at most 50 characters")
    private String district;

    @NotEmpty
    @Size(max=10, message = "Address number must have at most 10 characters")
    private String number;

    @NotEmpty
    @Size(max=2, message = "State must have at most 2 characters")
    private String state;

    @NotEmpty
    @Size(max=50, message = "Street must have at most 50 characters")
    private String street;

    @NotEmpty
    @Size(max=12, message = "Zip code must have at most 12 characters")
    private String zipCode;
}
