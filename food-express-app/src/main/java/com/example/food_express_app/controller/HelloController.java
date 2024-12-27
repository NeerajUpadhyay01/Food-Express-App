package com.example.food_express_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/app")
public class HelloController {

    @GetMapping("/hello")
    public String greet(Model model) {
        return "hello"; // Ensure this matches the name of your hello.html file
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Ensure this matches the name of your login.html file
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Here you would typically check the username and password against a database
        // For demonstration, we will assume a simple check
        if ("user".equals(username) && "password".equals(password)) {
            model.addAttribute("uname", username);
            model.addAttribute("upassword", password);
            return "home"; // Redirect to hello.html
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Redirect back to login.html
        }
    }
}