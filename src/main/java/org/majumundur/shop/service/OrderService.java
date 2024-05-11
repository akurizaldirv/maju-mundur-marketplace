package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.Order;
import org.majumundur.shop.model.request.order.OrderRequest;
import org.majumundur.shop.model.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest  orderRequest, String authHeader);
    Order getOrderById(Integer id);
    OrderResponse getById(Integer id);
    List<OrderResponse> getAll();
    void throwIfIdNotExist(Integer id);
}
