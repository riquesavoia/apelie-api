package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.mappers.StoreMapper;
import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.User;
import com.apelie.apelieapi.repositories.StoreRepository;
import com.apelie.apelieapi.services.FileService;
import com.apelie.apelieapi.services.StoreService;
import com.apelie.apelieapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger LOGGER = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Override
    public List<Store> getAllStores(Specification<Store> specifications) {
        try {
            List<Store> stores = storeRepository.findAll(specifications);
            return stores;
        } catch (Exception e) {
            LOGGER.error("Error when getting all stores");
            throw e;
        }
    }

    @Override
    public Store getStoreById(Long id) {
        try {
            return storeRepository
                    .findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Store not found"));
        } catch (Exception e) {
            LOGGER.error("Error when getting store by id");
            throw e;
        }
    }

    @Override
    public void createStore(CreateStoreDTO createStoreDTO) {
        try {
            User owner = userService.getLoggedUser();

            if (storeRepository.findByOwnerUserId(owner.getUserId()).isPresent()) {
                throw new EntityExistsException("This user already has a store");
            }

            Store store = StoreMapper.toEntity(createStoreDTO);
            store.setOwner(owner);

            String bannerUrl;
            String logoUrl;
            try {
                bannerUrl = fileService.uploadFile(createStoreDTO.getBannerImage());
                logoUrl = fileService.uploadFile(createStoreDTO.getLogoImage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error when uploading image file");
            }

            store.setBannerUrl(bannerUrl);
            store.setLogoUrl(logoUrl);

            storeRepository.save(store);
        } catch (Exception e) {
            LOGGER.error("Error when creating a new store", e);
            throw e;
        }
    }

    @Override
    public void updateStore(CreateStoreDTO createStoreDTO) {
        try {
            User storeOwner = userService.getLoggedUser();

            Store currentStore = storeRepository
                    .findByOwnerUserId(storeOwner.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("You don't have a store to update"));

            if (currentStore.getOwner().getUserId() != storeOwner.getUserId()) {
                throw new AccessControlException("You don't have permission to edit this store");
            }

            Store updatedStore = StoreMapper.toEntity(createStoreDTO, currentStore);

            try {
                if (createStoreDTO.getBannerImage() != null) {
                    this.fileService.deleteImageByUrl(currentStore.getBannerUrl());
                    updatedStore.setBannerUrl(this.fileService.uploadFile(createStoreDTO.getBannerImage()));
                }

                if (createStoreDTO.getLogoImage() != null) {
                    this.fileService.deleteImageByUrl(currentStore.getLogoUrl());
                    updatedStore.setLogoUrl(this.fileService.uploadFile(createStoreDTO.getLogoImage()));
                }
            } catch(Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error when uploading image file");
            }


            storeRepository.save(updatedStore);
        } catch (Exception e) {
            LOGGER.error("Error when trying to update store", e);
            throw e;
        }
    }

    @Override
    public boolean storeExistsByUserId(Long userId) {
        try {
            return this.storeRepository.existsByOwnerUserId(userId);
        } catch (Exception e) {
            LOGGER.error("Error when checking if user has store", e);
            throw e;
        }
    }
}
