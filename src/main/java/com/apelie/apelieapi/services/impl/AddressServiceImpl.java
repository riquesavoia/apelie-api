package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.address.CreateAddressDto;
import com.apelie.apelieapi.controllers.dto.address.UpdateAddressDto;
import com.apelie.apelieapi.mappers.AddressMapper;
import com.apelie.apelieapi.models.Address;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.repositories.AddressRepository;
import com.apelie.apelieapi.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(CreateAddressDto createAddressDto, User user) {
        Address address = AddressMapper.toEntity(createAddressDto);
        address.setUser(user);
        addressRepository.save(address);

        return address;
    }

    @Override
    public Address updateAddress(UpdateAddressDto updateAddressDto, User user) {
        Address currentAddress = addressRepository.findById(updateAddressDto.getAddressId()).orElseThrow(() -> new NoSuchElementException("Address not found"));

        if (currentAddress.getUser().getUserId() != user.getUserId()) {
            throw new AccessControlException("You don't have permission to edit this address");
        }

        Address updatedAddress = AddressMapper.toEntity(updateAddressDto, currentAddress);

        addressRepository.save(updatedAddress);
        return updatedAddress;
    }

    @Override
    public void deleteAddress(Long id, User user) {
        if (id == null) {
            throw new RuntimeException("Address ID cannot be null");
        }

        Address address = addressRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Address not found"));

        if (address.getUser().getUserId() != user.getUserId()) {
            throw new AccessControlException("You don't have permission to remove this address");
        }

        addressRepository.delete(address);
    }

    @Override
    public List<Address> getAllByUserId(Long id) {
        if (id == null) {
            throw new RuntimeException("User ID cannot be null");
        }

        return addressRepository.findAllByUserUserId(id);
    }
}
