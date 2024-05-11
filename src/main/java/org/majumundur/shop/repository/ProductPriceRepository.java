package org.majumundur.shop.repository;

import org.majumundur.shop.model.entity.ProductPrice;
import org.majumundur.shop.util.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {
    Optional<ProductPrice> findByProduct_IdAndStatus(Integer productId, EStatus status);

    @Modifying
    @Query("update m_product_price p set p.status=:status where p.id=:id")
    void updateStatus(EStatus status, Integer id);

    @Modifying
    @Query("update m_product_price p set p.stock=:stock where p.id=:id")
    void updateStock(Integer stock, Integer id);
}
