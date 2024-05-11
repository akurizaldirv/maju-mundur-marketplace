package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.AppUser;
import org.majumundur.shop.model.entity.Customer;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.customer.RegisterCustomerRequest;
import org.majumundur.shop.model.response.customer.CustomerResponse;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;

import java.util.List;

public interface CustomerService {
    Customer create(UserCredential userCredential, RegisterCustomerRequest request);
    Customer getCustomerById(Integer id);
    Customer getCustomerByUserId(Integer id);
    CustomerResponse getById(Integer id);
    SimpleCustomerResponse getSimpleById(Integer id);
    List<SimpleCustomerResponse> getAll();
    List<CustomerResponse> getCustomerOrderListByMerchant(String authHeader);
    void updatePoint(Integer point, Integer id);
    void delete(Integer id);
    void throwIfIdNotExist(Integer id);
    void throwIfNotValidated(RegisterCustomerRequest request);
}
