package com.lp.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        //Consider you are getting user info after log in.

      //  model.addAttribute("userName" , "Lalit Patil");
        //model.addAttribute("role", "Admin");

        model.addAttribute("userName" , "Sunil Bhoi");
        model.addAttribute("role", "User");

        return "dashboard";
    }

    //The th:switch and th:case Attributes
    @GetMapping("/menu")
    public String showMenu(Model model){
        model.addAttribute("role", "");
        return "menu";
    }






}
