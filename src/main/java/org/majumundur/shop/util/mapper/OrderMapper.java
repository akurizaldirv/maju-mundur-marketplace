package org.majumundur.shop.util.mapper;

import org.majumundur.shop.model.entity.Customer;
import org.majumundur.shop.model.entity.Order;
import org.majumundur.shop.model.entity.OrderDetail;
import org.majumundur.shop.model.entity.ProductPrice;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;
import org.majumundur.shop.model.response.order.OrderDetailResponse;
import org.majumundur.shop.model.response.order.OrderResponse;
import org.majumundur.shop.model.response.product.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {
    public static OrderDetailResponse mapToDetailRes(OrderDetail orderDetail, ProductResponse productResponse) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .qty(orderDetail.getQty())
                .product(productResponse)
                .build();
    }

    public static OrderResponse mapToRes(Order order, SimpleCustomerResponse customerResponse, List<OrderDetailResponse> orderDetailResponses) {
        return OrderResponse.builder()
                .id(order.getId())
                .customer(customerResponse)
                .orders(orderDetailResponses)
                .build();
    }

    public static Order mapToEntity(Customer customer) {
        return Order.builder()
                .transDate(LocalDateTime.now())
                .customer(customer)
                .build();
    }

    public static OrderDetail mapToDetailEntity(Order order, ProductPrice productPrice, Integer qty) {
        return OrderDetail.builder()
                .order(order)
                .productPrice(productPrice)
                .qty(qty)
                .build();
    }
}
