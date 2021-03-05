package lux.pe.na.store.security.service;

import lux.pe.na.store.security.dto.UserDto;
import lux.pe.na.store.security.dto.UserEditDto;
import lux.pe.na.store.security.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto getById(Long id);

    User getByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByMail(String mail);

    void save(UserDto userDto);

    UserEditDto update(Long id, UserEditDto userEditDto);
}
