package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.*;
import com.geekbrains.geekmarketwinter.repositories.OrderRepository;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderStatusService orderStatusService;
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderStatusService orderStatusService, DeliveryAddressService deliveryAddressService) {
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
        this.deliveryAddressService = deliveryAddressService;
    }

    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getTotalCost());
        order.setStatus(orderStatusService.findByTitle("Сформирован"));
        order.setDeliveryAddress(deliveryAddressService.findFirstByUserId(user.getId()));
        order.setPhoneNumber("-");
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        order.setDeliveryPrice(0.0);
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        orderRepository.save(order);
        return order;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findOrderById(id);
    }

    public List<Order> findByUserId(Long id){
        List<Order> listOrder = orderRepository.findAllByUserId(id);
        return listOrder;
    }
}
