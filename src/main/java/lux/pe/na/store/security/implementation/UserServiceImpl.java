package lux.pe.na.store.security.implementation;

import lux.pe.na.store.security.dto.UserDto;
import lux.pe.na.store.security.dto.UserEditDto;
import lux.pe.na.store.security.entity.Role;
import lux.pe.na.store.security.entity.User;
import lux.pe.na.store.security.enums.RoleName;
import lux.pe.na.store.security.repository.UserRepository;
import lux.pe.na.store.security.service.UserService;

import static lux.pe.na.store.utils.DataStatus.*;

import lux.pe.na.store.validation.ValidNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDto convertToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    private UserEditDto convertToEditDto(User user) {
        return mapper.map(user, UserEditDto.class);
    }


    @Override
    public List<UserDto> findAll() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return convertToDto(repository.findById(id)
                .orElseThrow(() -> new ValidNotFoundException(USER_ID + id)));
    }

    @Override
    public User getByUserName(String userName) {
        return repository.findByUserName(userName)
                .orElseThrow(() -> new ValidNotFoundException(USER_NAME + userName));
    }

    @Override
    public boolean existsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }

    @Override
    public boolean existsByMail(String mail) {
        return repository.existsByMail(mail);
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User()
                .setName(userDto.getName())
                .setUserName(userDto.getUserName())
                .setMail(userDto.getMail())
                .setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByName(RoleName.ROLE_USER));
        if (userDto.getRoles().contains("admin"))
            roles.add(roleService.getByName(RoleName.ROLE_ADMIN));
        user.setRoles(roles);
        convertToDto(repository.save(user));
    }

    @Override
    public UserEditDto update(Long id, UserEditDto userEditDto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ValidNotFoundException(USER_ID + id));
        user.setName(userEditDto.getName());
        user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByName(RoleName.ROLE_USER));
        if (userEditDto.getRoles().contains("admin"))
            roles.add(roleService.getByName(RoleName.ROLE_ADMIN));
        user.setRoles(roles);
        return convertToEditDto(repository.save(user));
    }
}
