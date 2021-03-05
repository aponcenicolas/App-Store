package lux.pe.na.store.security.service;

import lux.pe.na.store.security.dto.RoleDto;
import lux.pe.na.store.security.entity.Role;
import lux.pe.na.store.security.enums.RoleName;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto save(RoleDto roleDto);

    Role getByName(RoleName roleName);
}
