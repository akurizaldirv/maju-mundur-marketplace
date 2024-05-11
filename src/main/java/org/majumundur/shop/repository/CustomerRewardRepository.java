package org.majumundur.shop.repository;

import org.majumundur.shop.model.entity.CustomerReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRewardRepository extends JpaRepository<CustomerReward, Integer> {
    List<CustomerReward> findAllByCustomer_Id(Integer id);
}
