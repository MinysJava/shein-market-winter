package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.User;

public interface IUserProvider {
    public User findByUserName(String userName);
}
