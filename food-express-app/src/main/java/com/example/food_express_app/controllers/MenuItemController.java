package com.example.food_express_app.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.food_express_app.entities.MenuItem;
import com.example.food_express_app.services.CloudinaryService;
import com.example.food_express_app.services.MenuItemService;

@Controller
@RequestMapping("/restaurant")
public class MenuItemController {
    @Autowired
    private MenuItemService service;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/{id}/menu")
    public String showMenuItemForm(@PathVariable Long id, Model model) {
        model.addAttribute("restaurantId", id);
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("menuItems", service.getMenuItemsByRestaurantId(id));
        return "restaurant/menu";
    }

    @PostMapping(value = "/{id}/menu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addMenuItem(@PathVariable Long id, @ModelAttribute MenuItem item,
            @RequestParam("file") MultipartFile file) {
        if (id == null || item == null) {
            throw new IllegalArgumentException("Restaurant ID and menu item cannot be null");
        }
        try {
            String imageUrl = cloudinaryService.uploadFile(file);
            item.setImage(imageUrl);
            service.addMenuItem(id, item);
            return "redirect:/{id}/menu";

        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public void deleteMenuItem(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Menu item ID cannot be null");
        }
        service.removeMenuItem(id);
    }

    @PutMapping(value = "/menu/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public MenuItem updateMenuItem(@PathVariable Long id, @ModelAttribute MenuItem menuItem,
            @RequestParam("file") MultipartFile file) {
        try {
            MenuItem existingMenuItem = service.getMenuItemById(id);
            if (existingMenuItem.getImage() != null) {
                String publicId = cloudinaryService.getPublicIdFromUrl(existingMenuItem.getImage());
                cloudinaryService.deleteFile(publicId);
            }
            String imageUrl = cloudinaryService.uploadFile(file);
            menuItem.setMenuItemId(id);
            return service.updateMenuItem(menuItem, imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    @PutMapping("/menu/{id}/stock")
    @ResponseBody
    public void updateStock(
            @PathVariable Long id,
            @RequestParam int stockQuantity,
            @RequestParam boolean available) {
        if (id == null) {
            throw new IllegalArgumentException("Menu item ID cannot be null");
        }
        service.updateStock(id, stockQuantity, available);
    }
}
