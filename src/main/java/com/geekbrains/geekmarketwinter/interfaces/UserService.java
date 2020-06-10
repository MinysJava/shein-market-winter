package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.SystemUser;
import com.geekbrains.geekmarketwinter.entites.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
