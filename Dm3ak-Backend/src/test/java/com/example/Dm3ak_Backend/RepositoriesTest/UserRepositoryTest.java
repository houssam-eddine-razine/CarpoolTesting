package com.example.Dm3ak_Backend.RepositoriesTest;

import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void testFindByEmail() {
//        User user = new User();
//        user.setEmail("user@example.com");
//        user.setName("John");
//        user.setFirstname("Doe");
//        userRepository.save(user);
//
//        Optional<User> foundUser = userRepository.findByEmail("user@example.com");
//        assertTrue(foundUser.isPresent());
//        assertEquals("John", foundUser.get().getName());
//    }
//}
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        // Persist User
        User user = new User();
        user.setEmail("user@example.com");
        user.setName("John");
        user.setFirstname("Doe");
        user.setPassword("password");
        userRepository.save(user); // Save User

        // Validate
        Optional<User> foundUser = userRepository.findByEmail("user@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals("John", foundUser.get().getName());
    }
}
