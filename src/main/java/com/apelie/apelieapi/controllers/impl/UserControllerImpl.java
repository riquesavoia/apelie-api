package com.apelie.apelieapi.controllers.impl;

import com.apelie.apelieapi.controllers.UserController;
import com.apelie.apelieapi.controllers.dto.address.AddressResponseDto;
import com.apelie.apelieapi.controllers.dto.address.CreateAddressDto;
import com.apelie.apelieapi.controllers.dto.address.UpdateAddressDto;
import com.apelie.apelieapi.controllers.dto.user.CreateUserDto;
import com.apelie.apelieapi.controllers.dto.user.UserResponseDto;
import com.apelie.apelieapi.mappers.AddressMapper;
import com.apelie.apelieapi.mappers.UserMapper;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.services.AddressService;
import com.apelie.apelieapi.services.StoreService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private AddressService addressService;

    @Override
    public void create(CreateUserDto createUserRequest) {
        this.userService.createUser(createUserRequest);
    }

    @Override
    public UserResponseDto getLoggedUser() {
        User loggedUser = this.userService.getLoggedUser();
        boolean hasStore = this.storeService.storeExistsByUserId(loggedUser.getUserId());

        return UserMapper.toDto(loggedUser, hasStore);
    }

    @Override
    public AddressResponseDto createAddress(CreateAddressDto createAddressDto) {
        return AddressMapper.toDto(addressService.createAddress(createAddressDto, userService.getLoggedUser()));
    }

    @Override
    public AddressResponseDto updateAddress(UpdateAddressDto updateAddressDto) {
        return AddressMapper.toDto(addressService.updateAddress(updateAddressDto, userService.getLoggedUser()));
    }

    @Override
    public void deleteAddress(Long id) {
        addressService.deleteAddress(id, userService.getLoggedUser());
    }

    @Override
    public List<AddressResponseDto> getAllAddresses() {
        return addressService.getAllByUserId(userService.getLoggedUser().getUserId())
                .stream()
                .map(AddressMapper::toDto)
                .collect(Collectors.toList());
    }
}
