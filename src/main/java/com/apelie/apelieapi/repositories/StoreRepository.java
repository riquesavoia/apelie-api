package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Store;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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
}
