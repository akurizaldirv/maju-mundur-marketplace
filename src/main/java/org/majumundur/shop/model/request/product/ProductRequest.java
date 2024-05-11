package org.majumundur.shop.model.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotNull(message = "price cannot be null")
    private Long price;
    @NotNull(message = "stock cannot be null")
    private Integer stock;
}
