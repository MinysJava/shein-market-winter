package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.OrderStatus;

public interface IOrderStatusProvider {

    OrderStatus findByTitle(String title);
}
