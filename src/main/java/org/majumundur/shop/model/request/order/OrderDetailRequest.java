package org.majumundur.shop.model.request.order;

import jakarta.validation.constraints.Min;
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
public class OrderDetailRequest {
    @NotNull(message = "productId cannot be null")
    private Integer productId;
    @NotNull(message = "qty cannot be null")
    @Min(value = 1, message = "qty cannot less than 1")
    private Integer qty;
}
