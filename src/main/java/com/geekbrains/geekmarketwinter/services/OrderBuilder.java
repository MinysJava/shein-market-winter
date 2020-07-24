package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.entites.User;

import java.time.LocalDateTime;

public class OrderBuilder {

    private Order order;

    public OrderBuilder() {
        this.order = new Order();
    }

    public OrderBuilder user(User user){
        order.setUser(user);
        return this;
    }

    public OrderBuilder price(double price){
        order.setPrice(price);
        return this;
    }

    public OrderBuilder status(OrderStatus status){
        order.setStatus(status);
        return this;
    }

    public OrderBuilder deliveryAddress(DeliveryAddress deliveryAddress){
        order.setDeliveryAddress(deliveryAddress);
        return this;
    }

    public OrderBuilder phoneNumber(String phoneNumber){
        order.setPhoneNumber(phoneNumber);
        return this;
    }

    public OrderBuilder deliveryDate(LocalDateTime deliveryDate){
        order.setDeliveryDate(deliveryDate);
        return this;
    }

    public OrderBuilder deliveryPrice(double deliveryPrice){
        order.setDeliveryPrice(deliveryPrice);
        return this;
    }

    public Order build(){
        return order;
    }
}

