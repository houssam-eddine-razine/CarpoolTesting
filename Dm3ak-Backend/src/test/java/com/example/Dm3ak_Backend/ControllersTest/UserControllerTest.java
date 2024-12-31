package com.example.Dm3ak_Backend.ControllersTest;


import com.example.Dm3ak_Backend.controller.UserController;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        passwordEncoder = new BCryptPasswordEncoder();
        UserController userController = new UserController(userService, passwordEncoder);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");

        // Corrected mocking
        when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));
    }

    @Test
    void testChangePassword() throws Exception {
        User user = new User();
        user.setPassword("$2a$10$7sU8uH/dO/DV9z5Q5g.TwO2qO93piFbqNJqDpVwm2vAqvN6ZoEjGi"); // Example of an encoded password
        when(userService.getUser(1L)).thenReturn(user);

        BCryptPasswordEncoder mockPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        when(mockPasswordEncoder.matches("oldPassword", user.getPassword())).thenReturn(true);
        when(mockPasswordEncoder.encode("newPassword")).thenReturn("$2a$10$newEncodedPasswordMock");

        UserController userController = new UserController(userService, mockPasswordEncoder);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(post("/api/users/password/change")
                        .param("userId", "1")
                        .param("oldPassword", "oldPassword")
                        .param("newPassword", "newPassword"))
                .andExpect(status().isOk())
                .andExpect(content().string("Password changed successfully!"));
    }

}
