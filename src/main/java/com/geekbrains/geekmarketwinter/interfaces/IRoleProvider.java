package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.Role;

public interface IRoleProvider {

    Role findOneByName(String roleName);
}
