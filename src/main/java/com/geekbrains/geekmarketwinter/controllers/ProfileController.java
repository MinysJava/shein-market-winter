package com.geekbrains.geekmarketwinter.controllers;


import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.services.OrderService;
import com.geekbrains.geekmarketwinter.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private OrderService orderService;
    private UserService userService;

    public ProfileController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String profile(Model model, HttpSession session, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        List<Order> listOrder = orderService.findByUserId(user.getId());
        model.addAttribute("orders", listOrder);
       return "profile";
    }





}
