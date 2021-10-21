package com.apelie.apelieapi.repositories;

import com.apelie.apelieapi.models.Store;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor<Store> {

    Optional<Store> findByOwnerUserId(Long userId);

    boolean existsByOwnerUserId(Long userId);

    @EntityGraph(attributePaths = {"productList", "productList.images", "owner", "categoryList"})
    List<Store> findAll(Specification<Store> specifications);
}
