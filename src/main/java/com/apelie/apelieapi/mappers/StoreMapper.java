package com.apelie.apelieapi.mappers;

import com.apelie.apelieapi.dto.store.CreateStoreDTO;
import com.apelie.apelieapi.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.models.Store;

public class StoreMapper {

    public static StoreResponseDTO toDto(Store store) {
        if (store == null) {
            return null;
        }

        return new StoreResponseDTO(
                store.getStoreId(),
                UserMapper.toDto(store.getOwner()),
                store.getTwitterLink(),
                store.getCategory(),
                store.getPaymentMethods(),
                store.getInstagramLink(),
                store.getState(),
                store.getFacebookLink(),
                store.getYoutubeLink(),
                store.getBannerUrl(),
                store.getTheme(),
                store.getStreet(),
                store.getCity(),
                store.getCep(),
                store.getName(),
                store.getEmail(),
                store.getPhone(),
                store.getAddressNumber(),
                store.getNeighbourhood(),
                store.getRating()
        );
    }

    public static Store toEntity(CreateStoreDTO createStoreDTO) {
        return null;
    }
}
