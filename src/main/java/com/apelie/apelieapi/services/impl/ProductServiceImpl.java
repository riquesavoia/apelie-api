package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.controllers.dto.product.CreateProductDTO;
import com.apelie.apelieapi.exception.FileSizeException;
import com.apelie.apelieapi.exception.FileTypeException;
import com.apelie.apelieapi.mappers.ProductMapper;
import com.apelie.apelieapi.models.Product;
import com.apelie.apelieapi.models.ProductImage;
import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.repositories.ProductRepository;
import com.apelie.apelieapi.services.FileService;
import com.apelie.apelieapi.services.ProductService;
import com.apelie.apelieapi.services.StoreService;
import com.apelie.apelieapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void deleteProduct(Long productId) {
        if (productId == null) {
            throw new RuntimeException("ProductID cannot be null");
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));

        if (product.getStore().getOwner().getUserId() != userService.getLoggedUser().getUserId()) {
            throw new AccessControlException("You don't have permission to remove this product");
        }

        productRepository.delete(product);

        for (ProductImage image : product.getImages()) {
            this.fileService.deleteImageByUrl(image.getUrl());
        }
    }

    @Override
    public List<Product> getAllProductsByStore(Long storeId) {
        if (storeId == null) {
            throw new RuntimeException("StoreID cannot be null");
        }

        Store store = storeService.getStoreById(storeId);

        if (store == null) {
            throw new NoSuchElementException("Store not found");
        }

        return productRepository.findAllByStoreStoreId(storeId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Override
    public void createProduct(CreateProductDTO createProductDTO, Long storeId) {
        if (storeId == null) {
            throw new RuntimeException("StoreID cannot be null");
        }

        Store store = storeService.getStoreById(storeId);

        if (store.getOwner().getUserId() != userService.getLoggedUser().getUserId()) {
            throw new AccessControlException("You don't have permission to add products into the store");
        }

        List<ProductImage> imageList = new ArrayList<>();

        try {
            for (String imageData: createProductDTO.getImages()) {
                String imageUrl = this.fileService.uploadFile(imageData);
                imageList.add(new ProductImage(imageUrl));
            }
        } catch (FileSizeException | FileTypeException e) {
            throw new RuntimeException("Invalid file or size too large");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Product product = ProductMapper.toEntity(createProductDTO);
        product.setStore(store);
        product.setImages(imageList);

        productRepository.save(product);
    }
}
