package com.example.food_express_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/app")
public class HelloController {

    @GetMapping("/hello")
    public String greet(Model model) {
        return "hello";
    }

}