package com.example.Dm3ak_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String firstname;
    private Date birthdate;
    private String phone;

    private Boolean prefSmoking;
    private Boolean prefAnimals;
    private Boolean prefTalk;

    private Double rating;

    @Embedded
    private Vehicle vehicle;

    @Embedded
    private PaymentMethod paymentMethod;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Boolean getPrefSmoking() { return prefSmoking; }
    public void setPrefSmoking(Boolean prefSmoking) { this.prefSmoking = prefSmoking; }
    public Boolean getPrefAnimals() { return prefAnimals; }
    public void setPrefAnimals(Boolean prefAnimals) { this.prefAnimals = prefAnimals; }
    public Boolean getPrefTalk() { return prefTalk; }
    public void setPrefTalk(Boolean prefTalk) { this.prefTalk = prefTalk; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
}

@Embeddable
class Vehicle {
    private String brand;
    private String model;
    private String registration;
    private String color;
    private int placeNumber;

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getRegistration() { return registration; }
    public void setRegistration(String registration) { this.registration = registration; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getPlaceNumber() { return placeNumber; }
    public void setPlaceNumber(int placeNumber) { this.placeNumber = placeNumber; }
}

@Embeddable
class PaymentMethod {
    private String cardNum;
    private String cardCvc;
    private Date cardExp;

    public String getCardNum() { return cardNum; }
    public void setCardNum(String cardNum) { this.cardNum = cardNum; }
    public String getCardCvc() { return cardCvc; }
    public void setCardCvc(String cardCvc) { this.cardCvc = cardCvc; }
    public Date getCardExp() { return cardExp; }
    public void setCardExp(Date cardExp) { this.cardExp = cardExp; }
}
