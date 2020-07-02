package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.*;
import com.geekbrains.geekmarketwinter.repositories.OrderRepository;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class OrderService {
    private final static String QUEUE_NAME = "order_queue";

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

    public void sendOrder(Order order) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false,false,false,null);
            channel.basicPublish("", QUEUE_NAME, null, order.toBytes());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
