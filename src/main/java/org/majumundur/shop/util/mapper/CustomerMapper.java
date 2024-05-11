package org.majumundur.shop.util.mapper;

import org.majumundur.shop.model.entity.Customer;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.customer.RegisterCustomerRequest;
import org.majumundur.shop.model.response.customer.CustomerResponse;
import org.majumundur.shop.model.response.customer.SimpleCustomerResponse;

public class CustomerMapper {
    public static Customer mapToEntity(RegisterCustomerRequest request, UserCredential userCredential) {
        return Customer.builder()
                .userCredential(userCredential)
                .identityNumber(request.getIdentityNumber())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .name(request.getName())
                .pointsObtained(0)
                .build();
    }
    public static CustomerResponse mapToRes(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .phone(customer.getPhone())
                .pointsObtained(customer.getPointsObtained())
                .name(customer.getName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .identityNumber(customer.getIdentityNumber())
                .build();
    }

    public static SimpleCustomerResponse mapToSimpleRes(Customer customer) {
        return SimpleCustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }
}
