package org.majumundur.shop.util.mapper;

import org.majumundur.shop.model.entity.AppUser;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.response.auth.LoginResponse;
import org.majumundur.shop.model.response.auth.RegisterResponse;
import org.majumundur.shop.model.response.auth.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthMapper {

    public static UserDetails mapToUserDetail(UserCredential userCredential) {
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getRole())
                .build();
    }

    public static RegisterResponse mapToRegisterRes(UserCredential userCredential) {
        return RegisterResponse.builder()
                .role(userCredential.getRole().getRole().name())
                .username(userCredential.getUsername())
                .build();
    }

    public static LoginResponse mapToLoginRes(UserCredential userCredential, String token) {
        return LoginResponse.builder()
                .role(userCredential.getRole().getRole().name())
                .username(userCredential.getUsername())
                .token(token)
                .build();
    }

    public static UserResponse mapToUserRes(UserCredential userCredential) {
        return UserResponse.builder()
                .role(userCredential.getRole().getRole().name())
                .username(userCredential.getUsername())
                .build();
    }
}
