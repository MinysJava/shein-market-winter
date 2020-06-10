package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.Role;

import java.util.List;

public interface IRoleProvider {

    Role findOneByName(String roleName);

    List<Role> findByUserId(Long id);
}
