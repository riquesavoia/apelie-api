package com.apelie.apelieapi.models.specifications;

import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.Store_;
import com.apelie.apelieapi.models.enums.StoreCategory;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class StoreSpecifications {

    public static Specification<Store> isNameLike(String name) {
        if (name == null) {
            return null;
        }

        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get(Store_.name), "%" + name + "%");
        });
    }

    public static Specification<Store> isRatingGreaterThan(Float rating) {
        if (rating == null) {
            return null;
        }

        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get(Store_.RATING), rating);
        });
    }

    public static Specification<Store> belongsToCategories(List<StoreCategory> categoryList) {
        if (categoryList == null) {
            return null;
        }

        return ((root, query, criteriaBuilder) -> {
            return root.join(Store_.CATEGORY_LIST).in(categoryList);
        });
    }

    public static Specification<Store> belongsToUser(Long ownerId) {
        if (ownerId == null) {
            return null;
        }

        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join(Store_.OWNER).get("userId"), ownerId);
        });
    }

    public static Specification<Store> isDeleted(boolean deleted) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Store_.DELETED), deleted);
        });
    }

    public static Specification<Store> hasProducts(boolean hasProducts) {
        return ((root, query, criteriaBuilder) -> {
            if (hasProducts) {
                return criteriaBuilder.isNotEmpty(root.get(Store_.PRODUCT_LIST));
            }
            return null;
        });
    }
}
