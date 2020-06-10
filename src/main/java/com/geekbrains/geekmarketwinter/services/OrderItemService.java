package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.OrderItem;
import com.geekbrains.geekmarketwinter.interfaces.IOrderItemProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private IOrderItemProvider orderItemProvider;

    @Autowired
    public void setOrderItems(IOrderItemProvider orderItems) {
        this.orderItemProvider = orderItems;
    }

    public void save(OrderItem orderItem) {
        orderItemProvider.save(orderItem);
    }


}
