package org.majumundur.shop.util.mapper;

import org.majumundur.shop.model.entity.Merchant;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.merchant.RegisterMerchantRequest;
import org.majumundur.shop.model.response.merchant.MerchantResponse;
import org.majumundur.shop.model.response.merchant.SimpleMerchantResponse;

public class MerchantMapper {
    public static Merchant mapToEntity(RegisterMerchantRequest request, UserCredential userCredential) {
        return Merchant.builder()
                .address(request.getAddress())
                .email(request.getEmail())
                .name(request.getName())
                .phone(request.getPhone())
                .userCredential(userCredential)
                .build();
    }

    public static MerchantResponse mapToRes(Merchant merchant) {
        return MerchantResponse.builder()
                .address(merchant.getAddress())
                .phone(merchant.getPhone())
                .name(merchant.getName())
                .email(merchant.getEmail())
                .id(merchant.getId())
                .build();
    }

    public static SimpleMerchantResponse mapToSimpleRes(Merchant merchant) {
        return SimpleMerchantResponse.builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .build();
    }
}
