package lux.pe.na.store.security.implementation;

import lux.pe.na.store.security.dto.RoleDto;
import lux.pe.na.store.security.entity.Role;
import lux.pe.na.store.security.enums.RoleName;
import lux.pe.na.store.security.repository.RoleRepository;
import lux.pe.na.store.security.service.RoleService;

import static lux.pe.na.store.utils.DataStatus.*;

import lux.pe.na.store.validation.ValidNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private RoleDto convertToDto(Role role) {
        return mapper.map(role, RoleDto.class);
    }

    private Role convertToEntity(RoleDto roleDto) {
        return mapper.map(roleDto, Role.class);
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = repository.findAll();
        return roles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        return convertToDto(repository.save(convertToEntity(roleDto)));
    }

    @Override
    public Role getByName(RoleName roleName) {
        return repository.findByRoleName(roleName)
                .orElseThrow(() -> new ValidNotFoundException(ROLE_NAME + roleName));
    }
}
