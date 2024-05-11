package org.majumundur.shop.model.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private Integer id;
    private SimpleCustomerResponse customer;
    private List<OrderDetailResponse> orders;
}
