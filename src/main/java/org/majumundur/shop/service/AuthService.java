package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.LoginRequest;
import org.majumundur.shop.model.request.auth.customer.RegisterCustomerRequest;
import org.majumundur.shop.model.request.auth.merchant.RegisterMerchantRequest;
import org.majumundur.shop.model.response.auth.LoginResponse;
import org.majumundur.shop.model.response.auth.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(RegisterCustomerRequest request);
    RegisterResponse registerMerchant(RegisterMerchantRequest request);
    LoginResponse login(LoginRequest request);
    void throwIfNotValidated(UserCredential credential);
}
