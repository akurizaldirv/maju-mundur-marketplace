package org.majumundur.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.Reward;
import org.majumundur.shop.repository.RewardRespository;
import org.majumundur.shop.service.RewardService;
import org.majumundur.shop.util.enums.EReward;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardRespository repository;

    @Override
    public Reward getOrSave(EReward reward) {
        Optional<Reward> newReward = repository.findByReward(reward);

        return newReward.orElseGet(() -> repository.saveAndFlush(
                Reward.builder()
                        .reward(reward)
                        .build()
        ));
    }
}
