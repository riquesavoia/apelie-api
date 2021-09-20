package com.apelie.apelieapi.services;

import com.apelie.apelieapi.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.models.Store;

import java.util.List;

public interface StoreService {

    /**
     * Gets all stores
     *
     * @return
     */
    List<Store> getAllStores();

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
