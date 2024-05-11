package org.majumundur.shop.repository;

import org.majumundur.shop.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    Optional<Merchant> findByUserCredentialUsername(String id);
    Optional<Merchant> findByUserCredential_Id(Integer id);
    long countByEmail(String email);
    long countByPhone(String phone);
}
