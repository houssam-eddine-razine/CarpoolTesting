package com.example.Dm3ak_Backend.service;

import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUser(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setBirthdate(updatedUser.getBirthdate());
        existingUser.setPrefSmoking(updatedUser.getPrefSmoking());
        existingUser.setPrefAnimals(updatedUser.getPrefAnimals());
        existingUser.setPrefTalk(updatedUser.getPrefTalk());
        existingUser.setVehicle(updatedUser.getVehicle());
        existingUser.setPaymentMethod(updatedUser.getPaymentMethod());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
