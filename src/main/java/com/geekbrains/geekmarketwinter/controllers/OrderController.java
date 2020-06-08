package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.OrderItem;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.interfaces.UserService;
import com.geekbrains.geekmarketwinter.services.*;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private ShoppingCartService shoppingCartService;

    private UserService userService;
    private DeliveryAddressService deliveryAddressService;
    private OrderItemService orderItemService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setDeliveryAddressService(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping("/fill")
    public String orderfill(Model model, HttpServletRequest httpServletRequest, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpServletRequest.getSession());
        Order order = new Order();
        order.setUser(user);
        order.setOrderItem(cart.getItems());
        order.setPrice(cart.getTotalCost());
        List<DeliveryAddress> deliveryAddress = deliveryAddressService.findByUserId(user.getId());
        model.addAttribute("order", order);
        model.addAttribute("deliveryAddresses", deliveryAddress);
        return "order-filler";
    }

    @PostMapping("/confirm")
    public String orderConfirm(Model model, HttpServletRequest httpServletRequest, @ModelAttribute(name = "order") Order orderFromFrontend, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpServletRequest.getSession());
        Order order = orderService.makeOrder(cart, user);
        order.setDeliveryAddress(orderFromFrontend.getDeliveryAddress());
        order.setPhoneNumber(orderFromFrontend.getPhoneNumber());
        List<Order> listOrder = orderService.findByUserId(user.getId());
        order = listOrder.get(listOrder.size() - 1);
        for (OrderItem oi: cart.getItems()) {
            oi.setOrder(order);
            orderItemService.save(oi);
        }
        order.setConfirmed(true);
        order.setOrderItem(cart.getItems());
        orderService.saveOrder(order);
        model.addAttribute("order", order);
        return "order-filler";
    }

    @GetMapping("/result/{id}")
    public String orderConfirm(Model model, @PathVariable(value = "id") Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        // todo ждем до оплаты, проверка безопасности и проблема с повторной отправкой письма сделать одноразовый вход
        User user = userService.findByUserName(principal.getName());
        Order confirmedOrder = orderService.findById(id);
        if (!user.getId().equals(confirmedOrder.getUser().getId())) {
            return "redirect:/";
        }
        model.addAttribute("order", confirmedOrder);
        return "order-result";
    }
}
