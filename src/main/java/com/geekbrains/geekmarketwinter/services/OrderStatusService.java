package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.interfaces.IOrderStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {
    private IOrderStatusProvider orderStatusProvider;

    @Autowired
    public void setOrderStatusProvider(IOrderStatusProvider orderStatusProvider) {
        this.orderStatusProvider = orderStatusProvider;
    }

    public OrderStatus findByTitle(String title){
        return orderStatusProvider.findByTitle(title);
    }
}
