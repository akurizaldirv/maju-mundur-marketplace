package org.majumundur.shop.model.request.auth.merchant;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterMerchantRequest {
    @NotBlank(message = "username cannot be blank")
    private String username;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "phone cannot be blank")
    private String phone;
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "address cannot be blank")
    private String address;
}
