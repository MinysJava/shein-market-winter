package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.User;

public interface IUserProvider {
     User findByUserName(String userName);

     void save (User user);
}
