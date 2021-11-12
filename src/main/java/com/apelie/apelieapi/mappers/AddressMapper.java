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

        Address address = new Address();
        address.setCity(createAddressDto.getCity());
        address.setComplement(createAddressDto.getComplement());
        address.setDistrict(createAddressDto.getDistrict());
        address.setNumber(createAddressDto.getNumber());
        address.setState(createAddressDto.getState());
        address.setStreet(createAddressDto.getStreet());
        address.setZipCode(createAddressDto.getZipCode());

        return address;
    }

    public static Address toEntity(UpdateAddressDto updateAddressDto, Address currentAddress) {
        if (updateAddressDto == null || currentAddress == null) {
            return null;
        }

        Address address = new Address();
        address.setAddressId(updateAddressDto.getAddressId());
        address.setCity(updateAddressDto.getCity());
        address.setComplement(updateAddressDto.getComplement());
        address.setDistrict(updateAddressDto.getDistrict());
        address.setNumber(updateAddressDto.getNumber());
        address.setState(updateAddressDto.getState());
        address.setStreet(updateAddressDto.getStreet());
        address.setZipCode(updateAddressDto.getZipCode());
        address.setUser(currentAddress.getUser());

        return address;
    }
}
