package com.example.Dm3ak_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class Carpool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User driver;

    private String departure;
    private String arrival;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Integer seats;
    private Boolean highway;
    private Double price;

    @ManyToMany
    private List<User> passengers = new ArrayList<>();

    private String description;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getDriver() { return driver; }
    public void setDriver(User driver) { this.driver = driver; }
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }
    public String getArrival() { return arrival; }
    public void setArrival(String arrival) { this.arrival = arrival; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }
    public Boolean getHighway() { return highway; }
    public void setHighway(Boolean highway) { this.highway = highway; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public List<User> getPassengers() { return passengers; }
    public void setPassengers(List<User> passengers) { this.passengers = passengers; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
