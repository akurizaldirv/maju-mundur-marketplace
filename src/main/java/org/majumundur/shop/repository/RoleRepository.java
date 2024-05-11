package org.majumundur.shop.repository;

import org.majumundur.shop.model.entity.Role;
import org.majumundur.shop.util.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(ERole role);
}
