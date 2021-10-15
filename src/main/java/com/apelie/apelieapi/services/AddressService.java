package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.address.CreateAddressDto;
import com.apelie.apelieapi.controllers.dto.address.UpdateAddressDto;
import com.apelie.apelieapi.models.Address;
import com.apelie.apelieapi.models.User;

import java.util.List;

public interface AddressService {

    /**
     * Creates a new address for logged user
     *
     * @param createAddressDto
     * @param user
     * @return
     */
    Address createAddress(CreateAddressDto createAddressDto, User user);

    /**
     * Updates an existing user address
     *
     * @param updateAddressDto
     * @param user
     * @return
     */
    Address updateAddress(UpdateAddressDto updateAddressDto, User user);

    /**
     * Deletes an user address
     *
     * @param id
     * @param user
     */
    void deleteAddress(Long id, User user);

    /**
     * Gets all user addresses
     *
     * @param id
     * @return
     */
    List<Address> getAllByUserId(Long id);
}
