package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.repositories.OrderRepository;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void add(ShoppingCart cart) {
        Order order = new Order();


//            order.setOrderItem(cart.getItems());
//            orderItem.setItemPrice(product.getPrice());
//            orderItem.setQuantity(0L);
//            orderItem.setId(0L);
//            orderItem.setTotalPrice(0.0);
//            items.add(orderItem);
//
//        orderItem.setQuantity(orderItem.getQuantity() + 1);
//        recalculate();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

}
