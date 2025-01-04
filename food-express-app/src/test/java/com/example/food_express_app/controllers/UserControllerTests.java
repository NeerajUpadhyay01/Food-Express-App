package com.example.food_express_app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.food_express_app.entities.User;
import com.example.food_express_app.services.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testShowLoginForm() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"))
                .andDo(print());
    }

    @Test
    public void testLoginUser() throws Exception {
        User user = new User();
        when(userService.login("user@gmail.com", "password")).thenReturn(user);

        mockMvc.perform(post("/login")
                .param("email", "user@gmail.com")
                .param("password", "password"))
                // .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/register"))
                .andDo(print());
    }

    @Test
    public void testRegisterUser() throws Exception {
        when(userService.registerUser(any(User.class))).thenReturn(new User());

        mockMvc.perform(post("/register")
                .param("username", "newuser")
                .param("password", "password")
                .param("email", "user@gmail.com"))
                // .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                // .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    public void testShowProfile() throws Exception {
        mockMvc.perform(get("/users/profile"))
                .andExpect(view().name("redirect:/users/login"))
                .andDo(print());
    }

    @Test
    public void testShowUpdateProfileForm() throws Exception {
        mockMvc.perform(get("/users/profile/update"))
                .andExpect(view().name("redirect:/users/login"))
                .andDo(print());
    }

    @Test
    public void testUpdateProfile() throws Exception {
        mockMvc.perform(post("/users/profile/update")
                .param("username", "updatedUser"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    public void testChangePassword() throws Exception {
        mockMvc.perform(post("/users/profile/change-Password")
                .param("oldPassword", "oldPassword")
                .param("newPassword", "newPassword"))
                // .andExpect(status().is3xxRedirection())
                .andDo(print());
    }
}
