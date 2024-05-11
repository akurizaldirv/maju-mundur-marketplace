package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.Merchant;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.merchant.RegisterMerchantRequest;
import org.majumundur.shop.model.response.merchant.MerchantResponse;
import org.majumundur.shop.model.response.merchant.SimpleMerchantResponse;

import java.util.List;

public interface MerchantService {
    Merchant create(UserCredential userCredential, RegisterMerchantRequest request);
    Merchant getMerchantById(Integer id);
    Merchant getMerchantByUserId(Integer id);
    MerchantResponse getById(Integer id);
    SimpleMerchantResponse getSimpleById(Integer id);
    List<MerchantResponse> getAll();
    //    MerchantResponse update(UpdateCustomerRequest request);
    void delete(Integer id);
    void throwIfIdNotExist(Integer id);
    void throwIfNotValidated(RegisterMerchantRequest request);
}
