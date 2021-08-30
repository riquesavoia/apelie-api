package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.mappers.StoreMapper;
import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.repositories.StoreRepository;
import com.apelie.apelieapi.services.FileService;
import com.apelie.apelieapi.services.StoreService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

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

    @Override
    public void createStore(CreateStoreDTO createStoreDTO) {
        Store store = StoreMapper.toEntity(createStoreDTO);
        User owner = userService.getLoggedUser();
        store.setOwner(owner);

        String bannerUrl = fileService.uploadFile(createStoreDTO.getBannerImage());
        String logoUrl = fileService.uploadFile(createStoreDTO.getLogoImage());

        store.setBannerUrl(bannerUrl);
        store.setLogoUrl(logoUrl);

        storeRepository.save(store);
    }
}
