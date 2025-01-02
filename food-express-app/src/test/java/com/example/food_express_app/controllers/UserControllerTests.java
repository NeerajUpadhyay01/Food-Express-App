package com.example.food_express_app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowLoginForm() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"));
    }

    @Test
    public void testLoginUser() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "user")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "newuser")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testShowUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"));
    }

    @Test
    public void testShowProfile() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));
    }

    @Test
    public void testShowUpdateProfileForm() throws Exception {
        mockMvc.perform(get("/profile/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateProfile"));
    }

    @Test
    public void testShowChangePasswordForm() throws Exception {
        mockMvc.perform(get("/profile/changePassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("changePassword"));
    }

    @Test
    public void testUpdateProfile() throws Exception {
        mockMvc.perform(post("/profile/update")
                .param("username", "updatedUser"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testChangePassword() throws Exception {
        mockMvc.perform(post("/profile/changePassword")
                .param("oldPassword", "oldPassword")
                .param("newPassword", "newPassword"))
                .andExpect(status().is3xxRedirection());
    }
}
