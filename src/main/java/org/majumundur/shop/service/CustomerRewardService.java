package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.CustomerReward;
import org.majumundur.shop.model.request.reward.RewardRequest;
import org.majumundur.shop.model.response.reward.RewardResponse;

import java.util.List;

public interface CustomerRewardService {
    RewardResponse claimReward(RewardRequest request, String authHeader);
    CustomerReward getCustomerRewardById(Integer id);
    RewardResponse getById(Integer id, String authHeader);
    List<RewardResponse> getAll(String authHeader);
}
