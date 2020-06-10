package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.OrderItem;

public interface IOrderItemProvider {
    void save(OrderItem orderItem);
}
