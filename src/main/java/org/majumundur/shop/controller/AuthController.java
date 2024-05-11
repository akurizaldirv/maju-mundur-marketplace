package org.majumundur.shop.controller;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.constant.AppPath;
import org.majumundur.shop.model.request.auth.LoginRequest;
import org.majumundur.shop.model.request.auth.customer.RegisterCustomerRequest;
import org.majumundur.shop.model.request.auth.merchant.RegisterMerchantRequest;
import org.majumundur.shop.model.response.SuccessResponse;
import org.majumundur.shop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BASE_AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(AppPath.REGISTER_CUSTOMER)
    public ResponseEntity<?> registerCustomer(@Validated @RequestBody RegisterCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        SuccessResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Register customer success")
                                .data(authService.registerCustomer(request))
                                .build()
                );
    }

    @PostMapping(AppPath.REGISTER_MERCHANT)
    public ResponseEntity<?> registerMerchant(@Validated @RequestBody RegisterMerchantRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        SuccessResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Register merchant success")
                                .data(authService.registerMerchant(request))
                                .build()
                );
    }

    @PostMapping(AppPath.LOGIN)
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        SuccessResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Login Success")
                                .data(authService.login(request))
                                .build()
                );
    }
}
