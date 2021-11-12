package com.apelie.apelieapi.services;

import com.apelie.apelieapi.controllers.dto.store.CreateStoreDTO;
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

    /**
     * Checks if given user has a store
     *
     * @param userId
     * @return
     */
    boolean storeExistsByUserId(Long userId);

    /**
     * Gets a store based on owner id
     *
     * @return
     */
    Store getStoreByUserId(Long userId);

    /**
     * Deletes a given store
     *
     * @param storeId
     */
    void deleteStore(Long storeId);
}
