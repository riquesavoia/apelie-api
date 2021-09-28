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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.security.AccessControlException;
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
    public List<Store> getAllStores(Specification<Store> specifications) {
        return storeRepository.findAll(specifications);
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Store not found"));
    }

    @Override
    public void createStore(CreateStoreDTO createStoreDTO) {
        User owner = userService.getLoggedUser();

        if (storeRepository.findByOwnerUserId(owner.getUserId()).isPresent()) {
            throw new EntityExistsException("This user already has a store");
        }

        Store store = StoreMapper.toEntity(createStoreDTO);
        store.setOwner(owner);

        String bannerUrl = fileService.uploadFile(createStoreDTO.getBannerImage());
        String logoUrl = fileService.uploadFile(createStoreDTO.getLogoImage());

        store.setBannerUrl(bannerUrl);
        store.setLogoUrl(logoUrl);

        storeRepository.save(store);
    }

    @Override
    public void updateStore(CreateStoreDTO createStoreDTO) {
        User storeOwner = userService.getLoggedUser();

        Store currentStore = storeRepository
                .findByOwnerUserId(storeOwner.getUserId())
                .orElseThrow(() -> new NoSuchElementException("You don't have a store to update"));

        if (currentStore.getOwner().getUserId() != storeOwner.getUserId()) {
            throw new AccessControlException("You don't have permission to edit this store");
        }

        Store updatedStore = StoreMapper.toEntity(createStoreDTO, currentStore);

        if (createStoreDTO.getBannerImage() != null) {
            this.fileService.deleteImageByUrl(currentStore.getBannerUrl());
            updatedStore.setBannerUrl(this.fileService.uploadFile(createStoreDTO.getBannerImage()));
        }

        if (createStoreDTO.getLogoImage() != null) {
            this.fileService.deleteImageByUrl(currentStore.getLogoUrl());
            updatedStore.setLogoUrl(this.fileService.uploadFile(createStoreDTO.getLogoImage()));
        }

        storeRepository.save(updatedStore);
    }
}
