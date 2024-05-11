package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.Reward;
import org.majumundur.shop.util.enums.EReward;

public interface RewardService {
    Reward getOrSave(EReward reward);
}
