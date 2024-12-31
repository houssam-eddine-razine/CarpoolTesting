package com.example.Dm3ak_Backend.EntityTest;

import com.example.Dm3ak_Backend.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class UserTest {


    @Test
    void testUserEntity() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setName("John");
        user.setFirstname("Doe");
        user.setBirthdate(new Date());
        user.setPhone("123456789");
        user.setPrefSmoking(true);
        user.setPrefAnimals(false);
        user.setPrefTalk(true);
        user.setRating(4.5);

        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getFirstname());
        assertNotNull(user.getBirthdate());
        assertEquals("123456789", user.getPhone());
        assertTrue(user.getPrefSmoking());
        assertFalse(user.getPrefAnimals());
        assertTrue(user.getPrefTalk());
        assertEquals(4.5, user.getRating());
    }
}
