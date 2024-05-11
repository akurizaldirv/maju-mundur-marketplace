package org.majumundur.shop.repository;

import org.majumundur.shop.model.entity.Product;
import org.majumundur.shop.util.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByIdAndStatus(Integer id, EStatus status);
    List<Product> findAllByStatus(EStatus status);
    @Modifying
    @Query("update m_product p set p.status=:status where p.id=:id")
    void updateStatus(EStatus status, Integer id);
}
