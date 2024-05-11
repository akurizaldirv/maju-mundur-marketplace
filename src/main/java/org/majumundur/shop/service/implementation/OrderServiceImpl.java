package org.majumundur.shop.service.implementation;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.*;
import org.majumundur.shop.model.request.order.OrderRequest;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;
import org.majumundur.shop.model.response.order.OrderDetailResponse;
import org.majumundur.shop.model.response.order.OrderResponse;
import org.majumundur.shop.model.response.product.ProductResponse;
import org.majumundur.shop.repository.OrderDetailRepository;
import org.majumundur.shop.repository.OrderRespository;
import org.majumundur.shop.service.CustomerService;
import org.majumundur.shop.service.OrderService;
import org.majumundur.shop.service.ProductService;
import org.majumundur.shop.util.exception.DataNotFoundException;
import org.majumundur.shop.util.mapper.CustomerMapper;
import org.majumundur.shop.util.mapper.OrderMapper;
import org.majumundur.shop.util.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRespository orderRespository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderResponse create(OrderRequest orderRequest, String authHeader) {
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(authHeader.substring(7));
        Customer customer = customerService.getCustomerByUserId(Integer.parseInt(userInfo.get("userId")));

        Order order = OrderMapper.mapToEntity(customer);

        List<OrderDetail> orderDetails = orderRequest.getOrders().stream().map(orderDetailRequest -> {
            ProductPrice productPrice = productService.getProductPriceByProductId(orderDetailRequest
                    .getProductId());

            Integer stock = productPrice.getStock();
            Integer qty = orderDetailRequest.getQty();
            if (qty > stock) {
                throw new ValidationException("Insufficient stock");
            }
            productService.updateStock(orderDetailRequest.getProductId(), stock - qty);

            OrderDetail orderDetail = OrderMapper.mapToDetailEntity(order, productPrice, qty);
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);

            return orderDetail;
        }).toList();

        orderRespository.save(order);
        customerService.updatePoint(5 * orderDetails.size(), customer.getId());

        SimpleCustomerResponse simpleCustomerResponse = CustomerMapper.mapToSimpleRes(customer);
        List<OrderDetailResponse> orderDetailResponses = orderDetails.stream().map(orderDetail -> {
            ProductResponse productResponse = productService.getById(orderDetail.getProductPrice().getProduct().getId());
            return OrderMapper.mapToDetailRes(orderDetail, productResponse);
        }).toList();

        return OrderMapper.mapToRes(order, simpleCustomerResponse, orderDetailResponses);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRespository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Order not found")
        );
    }

    @Override
    public OrderResponse getById(Integer id) {
        Order order = this.getOrderById(id);
        SimpleCustomerResponse customerResponse = customerService.getSimpleById(order.getCustomer().getId());
        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream().map(orderDetail -> {
            ProductResponse productResponse = productService.getHistoryByProductPriceId(orderDetail.getProductPrice().getId());
            return OrderMapper.mapToDetailRes(orderDetail, productResponse);
        }).toList();

        return OrderMapper.mapToRes(order, customerResponse, orderDetailResponses);
    }

    @Override
    public List<OrderResponse> getAll() {
        List<Order> orders = orderRespository.findAll();

        return orders.stream().map(
                order -> this.getById(order.getId())
        ).toList();
    }

    @Override
    public void throwIfIdNotExist(Integer id) {
        this.getOrderById(id);
    }
}
