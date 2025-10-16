package com.lp.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        //Consider you are getting user info after log in.

        model.addAttribute("userName" , "Lalit Patil");
        model.addAttribute("role", "admin");

        return "dashboard";
    }
}
