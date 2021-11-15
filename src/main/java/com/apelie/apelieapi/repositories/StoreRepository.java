package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Store;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor<Store> {

    @EntityGraph(attributePaths = {"productList", "productList.images", "owner", "categoryList"})
    @Query("SELECT s FROM Store s WHERE s.owner.userId = ?1 and s.deleted = false")
    Optional<Store> findByOwnerUserId(Long userId);

    @Query("SELECT CASE WHEN count(s)> 0 then true else false end from Store s WHERE s.owner.userId = ?1 and s.deleted = false")
    boolean existsByOwnerUserId(Long userId);

    @EntityGraph(attributePaths = {"productList", "productList.images", "owner", "categoryList"})
    List<Store> findAll(Specification<Store> specifications);

    @Transactional
    @Modifying
    @Query("UPDATE Store s SET rating=(SELECT avg(sr.rating) FROM StoreReview sr WHERE sr.store.storeId = ?1) WHERE s.storeId = ?1")
    void updateStoreRating(Long storeId);
}
