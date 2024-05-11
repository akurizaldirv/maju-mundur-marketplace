package org.majumundur.shop.model.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.majumundur.shop.model.response.merchant.SimpleMerchantResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private SimpleMerchantResponse merchant;
}
