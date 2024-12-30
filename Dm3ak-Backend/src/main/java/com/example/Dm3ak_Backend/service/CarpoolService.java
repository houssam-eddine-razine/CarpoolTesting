package com.example.Dm3ak_Backend.service;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.repository.CarpoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class CarpoolService {

    @Autowired
    private CarpoolRepository carpoolRepository;

    public List<Carpool> getAllCarpools() {
        return carpoolRepository.findAll();
    }

    public Carpool getCarpoolById(Long id) {
        return carpoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carpool not found with id: " + id));
    }

    public List<Carpool> searchCarpools(String departure, String arrival, String date, Integer seats) {
        return carpoolRepository.findAll().stream()
                .filter(carpool -> carpool.getDeparture().equalsIgnoreCase(departure) &&
                        carpool.getArrival().equalsIgnoreCase(arrival) &&
                        (date == null || carpool.getDate().compareTo(new Date(date)) == 0) &&
                        (seats == null || carpool.getSeats() >= seats))
                .toList();
    }

    public Carpool saveCarpool(Carpool carpool) {
        return carpoolRepository.save(carpool);
    }

    public Carpool updateCarpool(Long id, Carpool updatedCarpool) {
        Carpool existingCarpool = getCarpoolById(id);
        existingCarpool.setDeparture(updatedCarpool.getDeparture());
        existingCarpool.setArrival(updatedCarpool.getArrival());
        existingCarpool.setDate(updatedCarpool.getDate());
        existingCarpool.setSeats(updatedCarpool.getSeats());
        existingCarpool.setHighway(updatedCarpool.getHighway());
        existingCarpool.setPrice(updatedCarpool.getPrice());
        existingCarpool.setDescription(updatedCarpool.getDescription());
        return carpoolRepository.save(existingCarpool);
    }

    public void deleteCarpool(Long id) {
        carpoolRepository.deleteById(id);
    }

    public void addPassengerToCarpool(Long carpoolId, User passenger) {
        Carpool carpool = getCarpoolById(carpoolId);
        if (carpool.getPassengers().size() >= carpool.getSeats()) {
            throw new RuntimeException("Carpool is already full");
        }
        carpool.getPassengers().add(passenger);
        carpoolRepository.save(carpool);
    }
}
