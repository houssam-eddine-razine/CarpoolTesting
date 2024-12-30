package com.example.Dm3ak_Backend.repository;

import com.example.Dm3ak_Backend.entity.Carpool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarpoolRepository extends JpaRepository<Carpool, Long> {
    // Custom query method to search carpools by departure and arrival
    List<Carpool> findByDepartureAndArrival(String departure, String arrival);

    // Additional query method to search by date and available seats
    List<Carpool> findByDepartureAndArrivalAndDateAndSeatsGreaterThanEqual(
            String departure, String arrival, java.util.Date date, Integer seats);
}
