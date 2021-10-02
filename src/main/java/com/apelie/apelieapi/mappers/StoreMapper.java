package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.controllers.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.controllers.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.models.Store;

import java.util.stream.Collectors;

public class StoreMapper {

    public static StoreResponseDTO toDto(Store store) {
        if (store == null) {
            return null;
        }

        return new StoreResponseDTO(
                store.getStoreId(),
                UserMapper.toDto(store.getOwner()),
                store.getTwitterAccount(),
                store.getCategoryList(),
                store.getProductList().stream().map(ProductMapper::toDto).collect(Collectors.toList()),
                store.getInstagramAccount(),
                store.getState(),
                store.getFacebookAccount(),
                store.getYoutubeAccount(),
                store.getBannerUrl(),
                store.getPrimaryColor(),
                store.getSecondaryColor(),
                store.getStreet(),
                store.getCity(),
                store.getCep(),
                store.getName(),
                store.getEmail(),
                store.getPhone(),
                store.getAddressNumber(),
                store.getNeighbourhood(),
                store.getRating(),
                store.getLogoUrl(),
                store.getDescription()
        );
    }

    public static Store toEntity(CreateStoreDTO createStoreDTO) {
        Store store = new Store();
        store.setEmail(createStoreDTO.getEmail());
        store.setCep(createStoreDTO.getCep());
        store.setState(createStoreDTO.getState());
        store.setNeighbourhood(createStoreDTO.getNeighbourhood());
        store.setInstagramAccount(createStoreDTO.getInstagramAccount());
        store.setYoutubeAccount(createStoreDTO.getYoutubeAccount());
        store.setCity(createStoreDTO.getCity());
        store.setFacebookAccount(createStoreDTO.getFacebookAccount());
        store.setDescription(createStoreDTO.getDescription());
        store.setAddressNumber(createStoreDTO.getAddressNumber());
        store.setStreet(createStoreDTO.getStreet());
        store.setPrimaryColor(createStoreDTO.getPrimaryColor());
        store.setSecondaryColor(createStoreDTO.getSecondaryColor());
        store.setPhone(createStoreDTO.getPhone());
        store.setTwitterAccount(createStoreDTO.getTwitterAccount());
        store.setName(createStoreDTO.getName());
        store.setCategoryList(createStoreDTO.getCategories());

        return store;
    }

    public static Store toEntity(CreateStoreDTO createStoreDTO, Store store) {
        store.setEmail(createStoreDTO.getEmail());
        store.setCep(createStoreDTO.getCep());
        store.setState(createStoreDTO.getState());
        store.setNeighbourhood(createStoreDTO.getNeighbourhood());
        store.setInstagramAccount(createStoreDTO.getInstagramAccount());
        store.setYoutubeAccount(createStoreDTO.getYoutubeAccount());
        store.setCity(createStoreDTO.getCity());
        store.setFacebookAccount(createStoreDTO.getFacebookAccount());
        store.setDescription(createStoreDTO.getDescription());
        store.setAddressNumber(createStoreDTO.getAddressNumber());
        store.setStreet(createStoreDTO.getStreet());
        store.setPrimaryColor(createStoreDTO.getPrimaryColor());
        store.setSecondaryColor(createStoreDTO.getSecondaryColor());
        store.setPhone(createStoreDTO.getPhone());
        store.setTwitterAccount(createStoreDTO.getTwitterAccount());
        store.setName(createStoreDTO.getName());
        store.setCategoryList(createStoreDTO.getCategories());

        return store;
    }
}
