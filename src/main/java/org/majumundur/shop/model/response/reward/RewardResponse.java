package org.majumundur.shop.model.response.reward;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RewardResponse {
    private Integer id;
    private String reward;
    private LocalDateTime redeemDate;
    private SimpleCustomerResponse customer;
}
