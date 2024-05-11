package org.majumundur.shop.repository;

import org.majumundur.shop.model.entity.Reward;
import org.majumundur.shop.util.enums.EReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RewardRespository extends JpaRepository<Reward, Integer> {
    Optional<Reward> findByReward(EReward reward);
}
