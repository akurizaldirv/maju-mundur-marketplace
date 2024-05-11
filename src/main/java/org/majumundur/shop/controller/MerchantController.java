package org.majumundur.shop.controller;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.constant.AppPath;
import org.majumundur.shop.model.response.SuccessResponse;
import org.majumundur.shop.model.response.merchant.MerchantResponse;
import org.majumundur.shop.service.MerchantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BASE_MERCHANT)
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        System.out.println("OK");
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<MerchantResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all merchants success")
                        .data(merchantService.getAll())
                        .build());
    }

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<MerchantResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get merchant success")
                        .data(merchantService.getById(id))
                        .build());
    }
}
