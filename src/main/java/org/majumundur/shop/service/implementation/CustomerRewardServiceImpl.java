package org.majumundur.shop.service.implementation;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.Customer;
import org.majumundur.shop.model.entity.CustomerReward;
import org.majumundur.shop.model.entity.Reward;
import org.majumundur.shop.model.request.reward.RewardRequest;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;
import org.majumundur.shop.model.response.reward.RewardResponse;
import org.majumundur.shop.repository.CustomerRewardRepository;
import org.majumundur.shop.service.CustomerRewardService;
import org.majumundur.shop.service.CustomerService;
import org.majumundur.shop.service.RewardService;
import org.majumundur.shop.util.enums.EReward;
import org.majumundur.shop.util.exception.DataNotFoundException;
import org.majumundur.shop.util.exception.UnauthorizedAccessException;
import org.majumundur.shop.util.mapper.CustomerMapper;
import org.majumundur.shop.util.mapper.CustomerRewardMapper;
import org.majumundur.shop.util.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerRewardServiceImpl implements CustomerRewardService {

    private final CustomerRewardRepository repository;
    private final RewardService rewardService;
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RewardResponse claimReward(RewardRequest request, String authHeader) {
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Customer customer = customerService.getCustomerByUserId(Integer.parseInt(userInfo.get("userId")));
        EReward eReward;

        try {
            eReward = EReward.valueOf(request.getReward().toUpperCase());
        } catch (Exception e) {
            throw new ValidationException("Reward unavailable");
        }

        if (eReward.getPrice() > customer.getPointsObtained()) {
            throw new ValidationException("Not enough point to claim this reward");
        }

        Reward reward = rewardService.getOrSave(eReward);
        CustomerReward customerReward = CustomerRewardMapper.mapToEntity(customer, reward);
        repository.save(customerReward);
        customerService.updatePoint(eReward.getPrice() * -1, customer.getId());

        SimpleCustomerResponse customerResponse = CustomerMapper.mapToSimpleRes(customer);
        return CustomerRewardMapper.mapToRes(customerReward, customerResponse);
    }

    @Override
    public CustomerReward getCustomerRewardById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Reward transaction not found")
        );
    }

    @Override
    public RewardResponse getById(Integer id, String authHeader) {
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Customer customer = customerService.getCustomerByUserId(Integer.parseInt(userInfo.get("userId")));
        CustomerReward customerReward = this.getCustomerRewardById(id);

        if (!Objects.equals(customerReward.getCustomer().getId(), customer.getId())) {
            throw new UnauthorizedAccessException("Only customers who receive rewards can access");
        }

        SimpleCustomerResponse customerResponse = CustomerMapper.mapToSimpleRes(customer);
        return CustomerRewardMapper.mapToRes(customerReward, customerResponse);
    }

    @Override
    public List<RewardResponse> getAll(String authHeader) {
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Customer customer = customerService.getCustomerByUserId(Integer.parseInt(userInfo.get("userId")));

        List<CustomerReward> customerRewards = repository.findAllByCustomer_Id(customer.getId());
        return customerRewards.stream().map(customerReward -> {
            SimpleCustomerResponse customerResponse = CustomerMapper.mapToSimpleRes(customerReward.getCustomer());
            return CustomerRewardMapper.mapToRes(customerReward, customerResponse);
        }).toList();
    }
}
