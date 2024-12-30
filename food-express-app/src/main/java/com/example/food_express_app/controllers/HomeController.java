package com.example.food_express_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home-page")
public class HomeController {
    @GetMapping("")
    public String index() {
        return "/index";
    }
}
