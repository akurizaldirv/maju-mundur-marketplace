package org.majumundur.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.majumundur.shop.model.entity.Role;
import org.majumundur.shop.repository.RoleRepository;
import org.majumundur.shop.service.RoleService;
import org.majumundur.shop.util.enums.ERole;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> newRole = repository.findByRole(role);

        return newRole.orElseGet(() -> repository.saveAndFlush(
                Role.builder()
                        .role(role)
                        .build()
        ));
    }
}
