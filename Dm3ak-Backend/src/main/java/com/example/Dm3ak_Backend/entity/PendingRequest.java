package com.example.Dm3ak_Backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class PendingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Carpool carpool;

    @OneToMany
    private List<User> passengers;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Carpool getCarpool() { return carpool; }
    public void setCarpool(Carpool carpool) { this.carpool = carpool; }
    public List<User> getPassengers() { return passengers; }
    public void setPassengers(List<User> passengers) { this.passengers = passengers; }
}
