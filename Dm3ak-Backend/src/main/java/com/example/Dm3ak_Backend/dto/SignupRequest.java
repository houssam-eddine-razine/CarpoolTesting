package com.example.Dm3ak_Backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private String firstname;
    private Date birthdate;
    private String phone;
    private Boolean prefSmoking;
    private Boolean prefAnimals;
    private Boolean prefTalk;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
