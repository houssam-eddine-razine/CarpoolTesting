package com.example.Dm3ak_Backend.EntityTest;

import com.example.Dm3ak_Backend.entity.Carpool;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class CarpoolTest {


    @Test
    void testCarpoolEntity() {
        Carpool carpool = new Carpool();
        carpool.setId(1L);
        carpool.setDeparture("New York");
        carpool.setArrival("Boston");
        carpool.setDate(new Date());
        carpool.setSeats(4);
        carpool.setHighway(true);
        carpool.setPrice(50.0);
        carpool.setDescription("Comfortable ride");

        assertEquals(1L, carpool.getId());
        assertEquals("New York", carpool.getDeparture());
        assertEquals("Boston", carpool.getArrival());
        assertEquals(4, carpool.getSeats());
        assertTrue(carpool.getHighway());
        assertEquals(50.0, carpool.getPrice());
        assertEquals("Comfortable ride", carpool.getDescription());
    }
}
