package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.*;
import com.geekbrains.geekmarketwinter.repositories.DeliveryAddressRepository;
import com.geekbrains.geekmarketwinter.repositories.OrderItemRepository;
import com.geekbrains.geekmarketwinter.repositories.OrderRepository;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderStatusService orderStatusService;
    private OrderItemService orderItemService;
//    private ShoppingCartService shoppingCartService;
//    private UserService userService;
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderStatusService orderStatusService, OrderItemService orderItemService, DeliveryAddressService deliveryAddressService) {
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
        this.orderItemService = orderItemService;
//        this.shoppingCartService = shoppingCartService;
//        this.userService = userService;
        this.deliveryAddressService = deliveryAddressService  ;
    }

    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
//        ShoppingCart cart = shoppingCartService.getCurrentCart(session);
//        for (OrderItem oi: cart.getItems()) {
//            orderItemService.save(oi);
//        }
//        DeliveryAddress deliveryAddress = new DeliveryAddress();
//        deliveryAddress.setAddress("-");
//        deliveryAddress.setId(0L);




//        order.setOrderItem(cart.getItems());
        order.setUser(user);
        order.setPrice(cart.getTotalCost());
        order.setStatus(orderStatusService.findByTitle("Сформирован"));
        order.setDeliveryAddress(deliveryAddressService.findFirstByUserId(user.getId()));
        order.setPhoneNumber("-");
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        order.setDeliveryPrice(0.0);
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        orderRepository.save(order);
//        order.setDeliveryAddress(deliveryAddressService.findByUserId(user.getId()));

//        order.setPrice(cart.getTotalCost());
//        order.setStatus(new OrderStatus());
//        User user = userService.findByUserName(principal.getName());
//        order.setUser(user);
//        order.setDeliveryAddress(new DeliveryAddress());
//        order.setStatus(new OrderStatus());
//            orderItem.setItemPrice(product.getPrice());
//            orderItem.setQuantity(0L);
//            orderItem.setId(0L);
//            orderItem.setTotalPrice(0.0);
//            items.add(orderItem);
//
//        orderItem.setQuantity(orderItem.getQuantity() + 1);
//        recalculate();
        return order;

    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findOrderById(id);
    }

//    public Order finalSetOrder(ShoppingCart currentCart, User user) {
//        return order;
//    }

    public List<Order> findByUserId(Long id){
        List<Order> listOrder = orderRepository.findAllByUserId(id);



        return listOrder;
    }
}
