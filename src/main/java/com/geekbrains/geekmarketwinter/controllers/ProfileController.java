package com.geekbrains.geekmarketwinter.controllers;


import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.services.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserServiceImpl userService;



    @GetMapping
    public String profile(Model model,
                          HttpSession session) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//
//        HttpSession session = attr.getRequest().getSession(true);
//        User user = userService.findByUserName(auth.getPrincipal().getClass().getName());
//
        model.addAttribute("session", session);



        return "profile";
    }




//    @GetMapping("/{username}")
//    public String profileUsername(Model model, @PathVariable(name = "username") String username) {
//        User user = userService.findByUserName(username);
//
//        model.addAttribute("user", user);
//
//        return "profile";
//    }
}
