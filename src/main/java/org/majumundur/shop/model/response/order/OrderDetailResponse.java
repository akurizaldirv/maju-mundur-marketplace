package org.majumundur.shop.model.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.majumundur.shop.model.response.product.ProductResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailResponse {
    private Integer id;
    private Integer qty;
    private ProductResponse product;
}
