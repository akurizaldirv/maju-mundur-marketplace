package org.majumundur.shop.controller;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.constant.AppPath;
import org.majumundur.shop.model.request.reward.RewardRequest;
import org.majumundur.shop.model.response.SuccessResponse;
import org.majumundur.shop.model.response.reward.RewardResponse;
import org.majumundur.shop.service.CustomerRewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BASE_REWARD)
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
public class RewardController {
    private final CustomerRewardService service;

    @PostMapping
    public ResponseEntity<?> claim(
            @Validated @RequestBody RewardRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(SuccessResponse.<RewardResponse>builder()
                       .statusCode(HttpStatus.CREATED.value())
                       .message("Claim reward success")
                       .data(service.claimReward(request, authHeader))
                       .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestHeader("Authorization") String authHeader
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<RewardResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all reward transaction success")
                        .data(service.getAll(authHeader))
                        .build());
    }

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<RewardResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get reward transaction success")
                        .data(service.getById(id, authHeader))
                        .build());
    }
}
