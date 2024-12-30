package com.example.food_express_app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            // return ResponseEntity.ok().body("You have logged in successfully!!");
            return "/home";
        } catch (RuntimeException e) {
            redirectAttributes.addAttribute("error", true);
            // return ResponseEntity.badRequest().body(e.getMessage());
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
        return "redirect:/home-page";
    }

    @GetMapping("/list")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/user/list";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
            redirectAttributes.addAttribute("registered", true);
            // return ResponseEntity.ok().body("You have registered successfully!!");
            return "redirect:/users/login";
        } catch (RuntimeException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            // return ResponseEntity.badRequest().body(e.getMessage());
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

    @GetMapping("/profile/update")
    public String showUpdateProfileForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/users/login";
        }
        model.addAttribute("user", user);
        return "/user/update-profile";
    }

    @GetMapping("/profile/change-password")
    public String showChangePasswordForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/users/login";
        }
        model.addAttribute("user", user);
        return "/user/change-password";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user, HttpSession session) {
        try {
            User updatedUser = userService.updateProfile(user);
            session.setAttribute("loggedInUser", updatedUser);
            // return ResponseEntity.ok().body("Profile updated successfully!!");
            return "redirect:/users/profile";
        } catch (Exception e) {
            // return ResponseEntity.badRequest().body(e.getMessage());
            return "redirect:/users/profile";
        }
    }

    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam String userId, @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        try {
            userService.changePassword(userId, oldPassword, newPassword);
            // return ResponseEntity.ok().body("Password changed successfully!!");
            return "redirect:/users/profile";
        } catch (Exception e) {
            // return ResponseEntity.badRequest().body(e.getMessage());
            return "redirect:/users/profile";
        }
    }

}