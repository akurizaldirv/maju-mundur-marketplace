package org.majumundur.shop.model.response.merchant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SimpleMerchantResponse {
    private Integer id;
    private String name;
}
