package org.majumundur.shop.service;


import org.majumundur.shop.model.entity.Role;
import org.majumundur.shop.util.enums.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}
