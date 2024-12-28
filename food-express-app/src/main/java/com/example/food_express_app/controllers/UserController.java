package com.example.food_express_app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.food_express_app.entities.User;
import com.example.food_express_app.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String name,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            User user = userService.login(name, password);
            session.setAttribute("loggedInUser", user);
            return "redirect:/users/profile";
        } catch (RuntimeException e) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/users/login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        userService.logout();
        return "redirect:/users/login";
    }

    @GetMapping("/list")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/user/list";
    }

    @PostMapping("/deactivate")
    public String deactivateAccount(@RequestParam String userId) {
        userService.deactivateAccount(userId);
        return "redirect:/users/list";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
            redirectAttributes.addAttribute("registered", true);
            return "redirect:/users/login";
        } catch (RuntimeException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/users/register";
        }
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/users/login";
        }
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user, HttpSession session) {
        User updatedUser = userService.updateProfile(user);
        session.setAttribute("loggedInUser", updatedUser);
        return "redirect:/users/profile";
    }
}