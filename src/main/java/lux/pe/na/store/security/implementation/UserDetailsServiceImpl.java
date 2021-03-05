package lux.pe.na.store.security.implementation;

import lux.pe.na.store.security.entity.PrimaryUser;
import lux.pe.na.store.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceImpl userService;

    @Autowired
    public UserDetailsServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getByUserName(username);
        return PrimaryUser.build(user);
    }
}
