package org.majumundur.shop.service.implementation;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.AppUser;
import org.majumundur.shop.model.entity.Role;
import org.majumundur.shop.model.entity.UserCredential;
import org.majumundur.shop.model.request.auth.LoginRequest;
import org.majumundur.shop.model.request.auth.customer.RegisterCustomerRequest;
import org.majumundur.shop.model.request.auth.merchant.RegisterMerchantRequest;
import org.majumundur.shop.model.response.auth.LoginResponse;
import org.majumundur.shop.model.response.auth.RegisterResponse;
import org.majumundur.shop.repository.UserCredentialRepository;
import org.majumundur.shop.service.*;
import org.majumundur.shop.util.enums.ERole;
import org.majumundur.shop.util.mapper.AuthMapper;
import org.majumundur.shop.util.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleService roleService;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final UserService userService;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerCustomer(RegisterCustomerRequest request) {
        Role role = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        UserCredential userCredential = UserCredential.builder()
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .build();

        this.throwIfNotValidated(userCredential);
        userCredentialRepository.save(userCredential);
        customerService.create(userCredential, request);

        return AuthMapper.mapToRegisterRes(userCredential);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerMerchant(RegisterMerchantRequest request) {
        Role role = roleService.getOrSave(ERole.ROLE_MERCHANT);
        UserCredential userCredential = UserCredential.builder()
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .build();

        this.throwIfNotValidated(userCredential);
        userCredentialRepository.save(userCredential);
        merchantService.create(userCredential, request);

        return AuthMapper.mapToRegisterRes(userCredential);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername().toLowerCase(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token =jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .username(appUser.getUsername())
                .role(appUser.getRole().name())
                .build();
    }

    @Override
    public void throwIfNotValidated(UserCredential credential) {
        UserCredential userCredential = userService.getUserByUsername(credential.getUsername());
        if (userCredential != null) {
            throw new ValidationException("Username already taken");
        }
    }
}
