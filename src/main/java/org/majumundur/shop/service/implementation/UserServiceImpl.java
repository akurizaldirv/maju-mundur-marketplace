package org.majumundur.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.Customer;
import org.majumundur.shop.model.entity.Merchant;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.response.auth.UserResponse;
import org.majumundur.shop.repository.UserCredentialRepository;
import org.majumundur.shop.service.CustomerService;
import org.majumundur.shop.service.MerchantService;
import org.majumundur.shop.service.UserService;
import org.majumundur.shop.util.exception.DataNotFoundException;
import org.majumundur.shop.util.mapper.AuthMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository repository;
    private final CustomerService customerService;
    private final MerchantService merchantService;

    @Override
    public UserDetails loadUserByUserId(Integer id) {
        UserCredential userCredential = repository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Credential ID not found")
        );

        return AuthMapper.mapToUserDetail(userCredential);
    }

    @Override
    public UserResponse getById(Integer id) {
        UserCredential userCredential = repository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Credential ID not found")
        );
        return AuthMapper.mapToUserRes(userCredential);
    }

    @Override
    public UserCredential getUserByCustomerId(Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return repository.findById(customer.getId()).orElseThrow(
                () -> new DataNotFoundException("Credential ID not found")
        );
    }

    @Override
    public UserCredential getUserByMerchantId(Integer id) {
        Merchant merchant = merchantService.getMerchantById(id);
        return repository.findById(merchant.getId()).orElseThrow(
                () -> new DataNotFoundException("Credential ID not found")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = repository.findByUsername(username).orElseThrow(
                () -> new DataNotFoundException("Username not found")
        );
        return AuthMapper.mapToUserDetail(userCredential);
    }

    @Override
    public UserCredential getUserByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }
}
