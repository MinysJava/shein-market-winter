package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.OrderItem;
import com.geekbrains.geekmarketwinter.interfaces.IOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private IOrderItem orderItems;

    @Autowired
    public OrderItemService(IOrderItem orderItem) {
        this.orderItems = orderItem;
    }

    public void save(OrderItem orderItem) {
        orderItems.save(orderItem);
    }


}
