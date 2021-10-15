package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.controllers.dto.address.AddressResponseDto;
import com.apelie.apelieapi.controllers.dto.address.CreateAddressDto;
import com.apelie.apelieapi.controllers.dto.address.UpdateAddressDto;
import com.apelie.apelieapi.models.Address;

public class AddressMapper {
    public static AddressResponseDto toDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressResponseDto addressResponseDto = new AddressResponseDto(
                address.getAddressId(),
                address.getCity(),
                address.getComplement(),
                address.getDistrict(),
                address.getNumber(),
                address.getState(),
                address.getStreet(),
                address.getZipCode()
        );

        return addressResponseDto;
    }

    public static Address toEntity(CreateAddressDto createAddressDto) {
        if (createAddressDto == null) {
            return null;
        }

        Address address = new Address(
                null,
                createAddressDto.getCity(),
                createAddressDto.getComplement(),
                createAddressDto.getDistrict(),
                createAddressDto.getNumber(),
                createAddressDto.getState(),
                createAddressDto.getStreet(),
                createAddressDto.getZipCode(),
                null
        );

        return address;
    }

    public static Address toEntity(UpdateAddressDto updateAddressDto, Address currentAddress) {
        if (updateAddressDto == null || currentAddress == null) {
            return null;
        }

        Address address = new Address(
                currentAddress.getAddressId(),
                updateAddressDto.getCity(),
                updateAddressDto.getComplement(),
                updateAddressDto.getDistrict(),
                updateAddressDto.getNumber(),
                updateAddressDto.getState(),
                updateAddressDto.getStreet(),
                updateAddressDto.getZipCode(),
                currentAddress.getUser()
        );

        return address;
    }
}
