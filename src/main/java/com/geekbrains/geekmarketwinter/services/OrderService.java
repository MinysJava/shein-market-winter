package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.*;
import com.geekbrains.geekmarketwinter.interfaces.IOrderProvider;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private IOrderProvider orderProvider;
    private OrderStatusService orderStatusService;
    private DeliveryAddressService deliveryAddressService;

    public OrderService(IOrderProvider orderProvider, OrderStatusService orderStatusService, DeliveryAddressService deliveryAddressService) {
        this.orderProvider = orderProvider;
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
        orderProvider.save(order);
        return order;
    }

    public void saveOrder(Order order) {
        orderProvider.save(order);
    }

    public Order findById(Long id) {
        return orderProvider.findOrderById(id);
    }

    public List<Order> findByUserId(Long id){
        List<Order> listOrder = orderProvider.findAllByUserId(id);
        return listOrder;
    }
}
