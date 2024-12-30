package com.example.Dm3ak_Backend.ServicesTest;

import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.repository.UserRepository;
import com.example.Dm3ak_Backend.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    public AuthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        User user = new User();
        user.setPassword("rawPassword");
        when(passwordEncoder.encode("rawPassword")).thenReturn("hashedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User result = authService.register(user);

        assertNotNull(result);
        assertEquals("hashedPassword", result.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testAuthenticate() {
        User user = new User();
        user.setPassword("hashedPassword");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("rawPassword", "hashedPassword")).thenReturn(true);

        Optional<User> result = authService.authenticate("test@example.com", "rawPassword");

        assertTrue(result.isPresent());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }
}
