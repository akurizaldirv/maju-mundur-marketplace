package org.majumundur.shop.util.mapper;

import org.majumundur.shop.model.entity.Merchant;
import org.majumundur.shop.model.entity.Product;
import org.majumundur.shop.model.entity.ProductPrice;
import org.majumundur.shop.model.request.product.ProductRequest;
import org.majumundur.shop.model.response.merchant.SimpleMerchantResponse;
import org.majumundur.shop.model.response.product.ProductResponse;
import org.majumundur.shop.util.enums.EStatus;

public class ProductMapper {
    public static Product mapToEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status(EStatus.ACTIVE)
                .build();
    }

    public static ProductResponse mapToRes(Product product, ProductPrice productPrice, SimpleMerchantResponse merchant) {
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .merchant(merchant)
                .build();
    }
}
