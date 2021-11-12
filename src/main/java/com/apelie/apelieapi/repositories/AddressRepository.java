package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.user.userId = ?1 AND deleted = false")
    List<Address> findAllByUserUserId(Long userId);
}
