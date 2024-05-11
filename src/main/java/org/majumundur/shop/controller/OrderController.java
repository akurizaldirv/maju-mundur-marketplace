package org.majumundur.shop.controller;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.constant.AppPath;
import org.majumundur.shop.model.request.order.OrderRequest;
import org.majumundur.shop.model.response.SuccessResponse;
import org.majumundur.shop.model.response.order.OrderResponse;
import org.majumundur.shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BASE_ORDER)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> create(
            @Validated @RequestBody OrderRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SuccessResponse.<OrderResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Create order success")
                        .data(orderService.create(request, authHeader))
                        .build());
    }

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<OrderResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get order success")
                        .data(orderService.getById(id))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<OrderResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all orders success")
                        .data(orderService.getAll())
                        .build());
    }
}
