package com.example.food_express_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/app")
public class HelloController {

    @GetMapping("/hello")
    public String greet(@RequestParam String name, Model model) {
        model.addAttribute("uname", name);
        model.addAttribute("user", "donjoe");
        model.addAttribute("location", "hyderabad");
        return "hello";
    }
}
