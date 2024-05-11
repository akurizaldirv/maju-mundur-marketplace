package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.Product;
import org.majumundur.shop.model.entity.ProductPrice;
import org.majumundur.shop.model.request.product.ProductRequest;
import org.majumundur.shop.model.request.product.ProductUpdateRequest;
import org.majumundur.shop.model.response.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request, String authHeader);
    Product getProductById(Integer id);
    ProductPrice getProductPriceByProductId(Integer id);
    ProductResponse getHistoryByProductPriceId(Integer id);
    ProductResponse getById(Integer id);
    List<ProductResponse> getAll();
    ProductResponse update(ProductUpdateRequest request, String authHeader);
    void updateStock(Integer id, Integer stock);
    void delete(Integer id, String authHeader);
    void throwIfIdNotExist(Integer id);
}
