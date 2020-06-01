package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.repositories.OrderRepository;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ShoppingCartService shoppingCartService;
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ShoppingCartService shoppingCartService, UserService userService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    public void saveOrder(HttpSession session, Principal principal) {
        Order order = new Order();
        ShoppingCart cart = shoppingCartService.getCurrentCart(session);



        order.setOrderItem(cart.getItems());

        order.setPrice(cart.getTotalCost());
//        order.setStatus(new OrderStatus());
        User user = userService.findByUserName(principal.getName());
        order.setUser(user);
        order.setDeliveryAddress(new DeliveryAddress());
        order.setStatus(new OrderStatus());
//            orderItem.setItemPrice(product.getPrice());
//            orderItem.setQuantity(0L);
//            orderItem.setId(0L);
//            orderItem.setTotalPrice(0.0);
//            items.add(orderItem);
//
//        orderItem.setQuantity(orderItem.getQuantity() + 1);
//        recalculate();
        saveOrder(order);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

}
