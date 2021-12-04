package com.apelie.apelieapi.services.impl;

import com.amazonaws.util.CollectionUtils;
import com.apelie.apelieapi.controllers.dto.product.CreateProductDTO;
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
    public Product updateProduct(CreateProductDTO updateProductDto, Long productId) {
        try {
            if (productId == null) {
                throw new RuntimeException("ProductID cannot be null");
            }

            Product actualProduct = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));

            if (actualProduct.getStore().getOwner().getUserId() != userService.getLoggedUser().getUserId()) {
                throw new AccessControlException("You don't have permission to edit this product");
            }

            Product productToUpdate = ProductMapper.toEntity(updateProductDto);
            productToUpdate.setImages(actualProduct.getImages());

            if (!CollectionUtils.isNullOrEmpty(updateProductDto.getImages())) {
                for (String imageData: updateProductDto.getImages()) {
                    String imageUrl = this.fileService.uploadFile(imageData);
                    productToUpdate.getImages().add(new ProductImage(imageUrl));
                }
            }

            productToUpdate.setProductId(actualProduct.getProductId());
            productToUpdate.setStore(actualProduct.getStore());

            productRepository.save(productToUpdate);
            return productToUpdate;
        } catch (Exception e) {
            LOGGER.error("Error on updating product", e);
            throw e;
        }
    }

    @Override
    public void deleteProductImage(Long productId, Long imageId) {
        try {
            Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));

            if (product.getImages().stream().noneMatch(image -> image.getProduct_image_id().equals(imageId))) {
                throw new NoSuchElementException("Image does not belong to this product");
            }

            if (product.getStore().getOwner().getUserId() != userService.getLoggedUser().getUserId()) {
                throw new AccessControlException("You don't have permission to remove this image");
            }

            ProductImage imageToBeRemoved = product.getImages().stream()
                    .filter(productImage -> productImage.getProduct_image_id().equals(imageId))
                    .findFirst()
                    .get();

            product.getImages().remove(imageToBeRemoved);
            fileService.deleteImageByUrl(imageToBeRemoved.getUrl());
            productRepository.save(product);
        } catch(Exception e) {
            LOGGER.error("Error when trying to remove product image", e);
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
            throw e;
        }
    }
}
