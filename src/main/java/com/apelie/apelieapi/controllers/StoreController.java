package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.dto.store.StoreResponseDTO;
import com.apelie.apelieapi.mappers.StoreMapper;
import com.apelie.apelieapi.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StoreResponseDTO> getAllStores() {
        return storeService.getAllStores()
                .stream()
                .map(StoreMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreResponseDTO getStoreById(@PathVariable Long id) {
        return StoreMapper.toDto(storeService.getStoreById(id));
    }
}
