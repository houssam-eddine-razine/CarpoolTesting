package com.example.Dm3ak_Backend.controller;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.service.CarpoolService;
import com.example.Dm3ak_Backend.service.PendingRequestService;
import com.example.Dm3ak_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carpools")
public class CarpoolController {

    private final CarpoolService carpoolService;
    private final PendingRequestService pendingRequestService;
    private final UserService userService;

    @Autowired
    public CarpoolController(CarpoolService carpoolService, PendingRequestService pendingRequestService, UserService userService) {
        this.carpoolService = carpoolService;
        this.pendingRequestService = pendingRequestService;
        this.userService = userService;
    }

    @GetMapping
    public List<Carpool> getAllCarpools() {
        return carpoolService.getAllCarpools();
    }

    @GetMapping("/{id}")
    public Carpool getCarpoolById(@PathVariable Long id) {
        return carpoolService.getCarpoolById(id);
    }

    @GetMapping("/search")
    public List<Carpool> searchCarpools(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Integer seats) {
        return carpoolService.searchCarpools(departure, arrival, date, seats);
    }

    @PostMapping
    public Carpool createCarpool(@RequestBody Carpool carpool, @RequestParam Long driverId) {
        User driver = userService.getUser(driverId);
        carpool.setDriver(driver);
        return carpoolService.saveCarpool(carpool);
    }

    @PutMapping("/{id}")
    public Carpool updateCarpool(@PathVariable Long id, @RequestBody Carpool updatedCarpool) {
        return carpoolService.updateCarpool(id, updatedCarpool);
    }

    @DeleteMapping("/{id}")
    public void deleteCarpool(@PathVariable Long id) {
        carpoolService.deleteCarpool(id);
    }

    @PostMapping("/{id}/book")
    public String bookCarpool(@PathVariable Long id, @RequestParam Long passengerId) {
        User passenger = userService.getUser(passengerId);
        carpoolService.addPassengerToCarpool(id, passenger);
        return "Booking successful!";
    }

    @PostMapping("/{id}/pending")
    public String addPendingRequest(@PathVariable Long id, @RequestParam Long passengerId) {
        Carpool carpool = carpoolService.getCarpoolById(id);
        User passenger = userService.getUser(passengerId);
        pendingRequestService.addPendingRequest(carpool, passenger);
        return "Request pending approval";
    }
}
