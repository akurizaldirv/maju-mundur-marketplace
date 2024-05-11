package org.majumundur.shop.controller;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.constant.AppPath;
import org.majumundur.shop.model.request.product.ProductRequest;
import org.majumundur.shop.model.request.product.ProductUpdateRequest;
import org.majumundur.shop.model.response.SuccessResponse;
import org.majumundur.shop.model.response.product.ProductResponse;
import org.majumundur.shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BASE_PRODUCT)
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MERCHANT')")
    public ResponseEntity<?> create(@Validated @RequestBody ProductRequest request,
                                    @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SuccessResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Create product success")
                        .data(productService.create(request, authHeader))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<ProductResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all product success")
                        .data(productService.getAll())
                        .build());
    }

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get product success")
                        .data(productService.getById(id))
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(
            @RequestBody ProductUpdateRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Update product success")
                        .data(productService.update(request, authHeader))
                        .build());
    }

    @DeleteMapping(AppPath.ID)
    public ResponseEntity<?> delete(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader
    ) {
        productService.delete(id, authHeader);
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Delete product success")
                        .data(null)
                        .build());
    }
}
