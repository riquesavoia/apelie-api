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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.util.*;
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

    private static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public void deleteProduct(Long productId) {
        try {
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
        } catch (Exception e) {
            LOGGER.error("Error when trying to remove a product", e);
            throw e;
        }
    }

    @Override
    public List<Product> getAllProductsByStore(Long storeId) {
        try {
            if (storeId == null) {
                throw new RuntimeException("StoreID cannot be null");
            }

            Store store = storeService.getStoreById(storeId);

            if (store == null) {
                throw new NoSuchElementException("Store not found");
            }

            return productRepository.findAllByStoreStoreId(storeId);
        } catch(Exception e) {
            LOGGER.error("Error on getting products from store", e);
            throw e;
        }
    }

    @Override
    public Product getProductById(Long productId) {
        try {
            return productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));
        } catch (Exception e) {
            LOGGER.error("Error on getting product by id", e);
            throw e;
        }
    }

    @Override
    public void createProduct(CreateProductDTO createProductDTO, Long storeId) {
        try {
            if (storeId == null) {
                throw new RuntimeException("StoreID cannot be null");
            }

            Store store = storeService.getStoreById(storeId);

            if (store.getOwner().getUserId() != userService.getLoggedUser().getUserId()) {
                throw new AccessControlException("You don't have permission to add products into the store");
            }

            Set<ProductImage> imageList = new HashSet<>();

            for (String imageData: createProductDTO.getImages()) {
                String imageUrl = this.fileService.uploadFile(imageData);
                imageList.add(new ProductImage(imageUrl));
            }

            Product product = ProductMapper.toEntity(createProductDTO);
            product.setStore(store);
            product.setImages(imageList);

            productRepository.save(product);
        } catch (Exception e) {
            LOGGER.error("Error when trying to create a product", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
