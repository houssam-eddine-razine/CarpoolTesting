package com.example.Dm3ak_Backend.DTOtest;

import com.example.Dm3ak_Backend.dto.LoginRequest;
import com.example.Dm3ak_Backend.dto.SignupRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DtoTest {

    @Test
    void testSignupRequestSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        SignupRequest request = new SignupRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        String json = mapper.writeValueAsString(request);
        SignupRequest deserialized = mapper.readValue(json, SignupRequest.class);

        assertEquals("test@example.com", deserialized.getEmail());
        assertEquals("password", deserialized.getPassword());
    }

    @Test
    void testLoginRequestSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        String json = mapper.writeValueAsString(request);
        LoginRequest deserialized = mapper.readValue(json, LoginRequest.class);

        assertEquals("test@example.com", deserialized.getEmail());
        assertEquals("password", deserialized.getPassword());
    }
}
