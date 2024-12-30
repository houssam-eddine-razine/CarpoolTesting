package com.example.Dm3ak_Backend.ControllersTest;

import com.example.Dm3ak_Backend.controller.AuthController;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.service.AuthService;
import com.example.Dm3ak_Backend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    private MockMvc mockMvc;
    private AuthService authService;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        authService = Mockito.mock(AuthService.class);
        jwtUtil = Mockito.mock(JwtUtil.class);
        AuthController authController = new AuthController(authService, jwtUtil);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testSignup() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Signup successful!"));
    }

    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");

        // Correctly mock the behavior of authenticate()
        when(authService.authenticate("test@example.com", "password")).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken("test@example.com")).thenReturn("fake-jwt-token");

        mockMvc.perform(post("/api/auth/login")
                        .param("email", "test@example.com")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(content().string("fake-jwt-token"));
    }
//    @Test
//    void testLoginWithInvalidCredentials() throws Exception {
//        when(authService.authenticate(anyString(), anyString())).thenReturn(Optional.empty());
//
//        mockMvc.perform(post("/api/auth/login")
//                        .param("email", "invalid@example.com")
//                        .param("password", "wrongPassword"))
//                .andExpect(status().isUnauthorized());
//    }
//    @Test
//    void testSignupWithInvalidInput() throws Exception {
//        User invalidUser = new User(); // Missing required fields
//
//        mockMvc.perform(post("/api/auth/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(invalidUser)))
//                .andExpect(status().isBadRequest());
//    }
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }



    @Test
    void testLoginWithoutCredentials() throws Exception {
        mockMvc.perform(post("/api/auth/login"))
                .andExpect(status().isBadRequest());
    }
}
