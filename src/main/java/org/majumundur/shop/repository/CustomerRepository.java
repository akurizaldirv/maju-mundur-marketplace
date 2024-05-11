package org.majumundur.shop.repository;

import org.majumundur.shop.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUserCredentialUsername(String username);
    Optional<Customer> findByUserCredential_Id(Integer id);
    long countByEmail(String email);
    long countByPhone(String phone);
    long countByIdentityNumber(String identityNumber);
    @Modifying
    @Query("update m_customer c set c.pointsObtained=:points where c.id=:id")
    void updatePoint(Integer points, Integer id);

    @Query(value = "select distinct c.* " +
            "from m_customer c join t_order o on c.id = o.customer_id " +
            "join t_order_detail od on o.id = od.order_id " +
            "join m_product_price pp on od.product_price_id = pp.id " +
            "where pp.merchant_id = :id", nativeQuery = true)
    List<Customer> getCustomerOrderListByMerchant(Integer id);
}
