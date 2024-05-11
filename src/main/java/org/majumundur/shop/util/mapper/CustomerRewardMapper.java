package org.majumundur.shop.util.mapper;

import org.majumundur.shop.model.entity.Customer;
import org.majumundur.shop.model.entity.CustomerReward;
import org.majumundur.shop.model.entity.Reward;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;
import org.majumundur.shop.model.response.reward.RewardResponse;

import java.time.LocalDateTime;

public class CustomerRewardMapper {
    public static CustomerReward mapToEntity(Customer customer, Reward reward) {
        return CustomerReward.builder()
                .customer(customer)
                .reward(reward)
                .redeemDate(LocalDateTime.now())
                .build();
    }

    public static RewardResponse mapToRes(CustomerReward customerReward, SimpleCustomerResponse customerResponse) {
        return RewardResponse.builder()
                .id(customerReward.getId())
                .customer(customerResponse)
                .reward(customerReward.getReward().getReward().name())
                .redeemDate(LocalDateTime.now())
                .build();
    }
}
