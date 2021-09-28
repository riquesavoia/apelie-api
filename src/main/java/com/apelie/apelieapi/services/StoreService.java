package com.apelie.apelieapi.services;

import com.apelie.apelieapi.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.models.Store;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface StoreService {

    /**
     * Gets all stores
     *
     * @return
     */
    List<Store> getAllStores(Specification<Store> specifications);

    /**
     * Gets a store by its id
     *
     * @param id
     * @return
     */
    Store getStoreById(Long id);

    /**
     * Creates a new store
     *
     * @param createStoreDTO
     */
    void createStore(CreateStoreDTO createStoreDTO);

    /**
     * Updates a store
     *
     * @param createStoreDTO
     */
    void updateStore(CreateStoreDTO createStoreDTO);
}
