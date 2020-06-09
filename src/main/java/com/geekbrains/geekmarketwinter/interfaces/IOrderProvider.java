package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.Order;

import java.util.List;

public interface IOrderProvider {

    Order findOrderById(Long id);

    List<Order> findAllByUserId(Long id);

    void save (Order order);
}
