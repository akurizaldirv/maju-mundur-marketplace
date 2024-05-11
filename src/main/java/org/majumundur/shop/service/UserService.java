package org.majumundur.shop.service;

import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.response.auth.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUserId(Integer id);
    UserResponse getById(Integer id);
    UserCredential getUserByCustomerId(Integer id);
    UserCredential getUserByMerchantId(Integer id);
    UserCredential getUserByUsername(String username);
}
