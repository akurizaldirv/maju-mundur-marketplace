package org.majumundur.shop.controller;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.constant.AppPath;
import org.majumundur.shop.model.response.SuccessResponse;
import org.majumundur.shop.model.response.customer.CustomerResponse;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;
import org.majumundur.shop.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BASE_CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<SimpleCustomerResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all customers success")
                        .data(customerService.getAll())
                        .build());
    }

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<CustomerResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get customer success")
                        .data(customerService.getById(id))
                        .build());
    }

    @GetMapping(AppPath.CUSTOMER_ORDER)
    @PreAuthorize("hasAnyRole('ROLE_MERCHANT')")
    public ResponseEntity<?> getCustomerOrderListByMerchant(
            @RequestHeader("Authorization") String authHeader
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<CustomerResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get ordered customer list success")
                        .data(customerService.getCustomerOrderListByMerchant(authHeader))
                        .build());
    }
}
