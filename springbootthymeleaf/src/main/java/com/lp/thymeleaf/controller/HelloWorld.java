package com.lp.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorld {

    @GetMapping("/hello")
    public String showhelloWorldPage(Model model){
         model.addAttribute("message", "Hello World: Today Lalit's Birthday!!");
        return "Hello";

    }

}
