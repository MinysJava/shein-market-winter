package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.repositories.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStatusService {
    private OrderStatusRepository orderStatusRepository;

    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public OrderStatus findByTitle(String title){
        return orderStatusRepository.findByTitle(title);
    }
}
