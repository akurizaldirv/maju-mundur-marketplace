package org.majumundur.shop.model.request.auth.customer;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCustomerRequest {
    @NotBlank(message = "identityNumber cannot be blank")
    private String identityNumber;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "username cannot be blank")
    private String username;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "phone cannot be blank")
    private String phone;
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "address cannot be blank")
    private String address;
}
