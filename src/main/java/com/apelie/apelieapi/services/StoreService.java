package com.apelie.apelieapi.services;

import com.apelie.apelieapi.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.models.Store;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();

    Store getStoreById(Long id);

    void createStore(CreateStoreDTO createStoreDTO);
}
