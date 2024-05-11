package org.majumundur.shop.model.response.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponse {
    private Integer id;
    private String identityNumber;
    private String name;
    private Integer pointsObtained;
    private String phone;
    private String email;
    private String address;
}
