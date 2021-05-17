package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.repositories.StoreRepository;
import com.apelie.apelieapi.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        List<Store> list = storeRepository.findAll();
        return list;
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Store not found"));
    }
}
