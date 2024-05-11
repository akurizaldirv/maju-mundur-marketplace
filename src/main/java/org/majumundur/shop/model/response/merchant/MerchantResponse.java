package org.majumundur.shop.model.response.merchant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MerchantResponse {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
}

