package com.example.food_express_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.food_express_app.entities.MenuItem;
import com.example.food_express_app.services.MenuItemService;

@Controller
@RequestMapping("/restaurant")
public class MenuItemController {
    @Autowired
    private MenuItemService service;

    @GetMapping("/menu")
    public String showMenu(Model model) {
        model.addAttribute("menuItems", service.getAvailableItems());
        return "restaurant/menu";
    }

    @PostMapping("/menu")
    public String addMenuItem(@ModelAttribute MenuItem item) {
        service.addMenuItem(item);
        return "redirect:/restaurant/menu";
    }

    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public void deleteMenuItem(@PathVariable Long id) {
        service.removeMenuItem(id);
    }

    @PutMapping("/menu/{id}")
    @ResponseBody
    public MenuItem updateMenuItem(@PathVariable Long id, @ModelAttribute MenuItem item) {
        return service.updateMenuItem(id, item);
    }

    @PutMapping("/menu/{id}/stock")
    @ResponseBody
    public void updateStock(
            @PathVariable Long id,
            @RequestParam int stockQuantity,
            @RequestParam boolean available) {
        service.updateStock(id, stockQuantity, available);
    }
}
