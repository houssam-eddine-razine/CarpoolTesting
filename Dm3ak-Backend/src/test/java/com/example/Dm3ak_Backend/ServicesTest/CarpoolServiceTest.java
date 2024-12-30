package com.example.Dm3ak_Backend.ServicesTest;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.repository.CarpoolRepository;
import com.example.Dm3ak_Backend.service.CarpoolService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarpoolServiceTest {

    @InjectMocks
    private CarpoolService carpoolService;

    @Mock
    private CarpoolRepository carpoolRepository;

    public CarpoolServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCarpools_HappyPath() {
        List<Carpool> carpools = new ArrayList<>();
        carpools.add(new Carpool());
        when(carpoolRepository.findAll()).thenReturn(carpools);

        List<Carpool> result = carpoolService.getAllCarpools();

        assertEquals(1, result.size());
        verify(carpoolRepository, times(1)).findAll();
    }

    @Test
    void testGetCarpoolById_Success() {
        Carpool carpool = new Carpool();
        carpool.setId(1L);
        when(carpoolRepository.findById(1L)).thenReturn(Optional.of(carpool));

        Carpool result = carpoolService.getCarpoolById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(carpoolRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCarpoolById_NotFound() {
        when(carpoolRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            carpoolService.getCarpoolById(1L);
        });

        assertEquals("Carpool not found with id: 1", exception.getMessage());
        verify(carpoolRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveCarpool_HappyPath() {
        Carpool carpool = new Carpool();
        when(carpoolRepository.save(carpool)).thenReturn(carpool);

        Carpool result = carpoolService.saveCarpool(carpool);

        assertNotNull(result);
        verify(carpoolRepository, times(1)).save(carpool);
    }

    @Test
    void testUpdateCarpool_Success() {
        Carpool existingCarpool = new Carpool();
        existingCarpool.setId(1L);
        existingCarpool.setDeparture("Old City");

        Carpool updatedCarpool = new Carpool();
        updatedCarpool.setDeparture("New City");

        when(carpoolRepository.findById(1L)).thenReturn(Optional.of(existingCarpool));
        when(carpoolRepository.save(any(Carpool.class))).thenReturn(existingCarpool);

        Carpool result = carpoolService.updateCarpool(1L, updatedCarpool);

        assertEquals("New City", result.getDeparture());
        verify(carpoolRepository, times(1)).save(existingCarpool);
    }

    @Test
    void testUpdateCarpool_NotFound() {
        Carpool updatedCarpool = new Carpool();
        when(carpoolRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            carpoolService.updateCarpool(1L, updatedCarpool);
        });

        assertEquals("Carpool not found with id: 1", exception.getMessage());
        verify(carpoolRepository, times(1)).findById(1L);
    }

    @Test
    void testAddPassengerToCarpool_Success() {
        Carpool carpool = new Carpool();
        carpool.setId(1L);
        carpool.setSeats(2);
        carpool.setPassengers(new ArrayList<>());

        User passenger = new User();
        when(carpoolRepository.findById(1L)).thenReturn(Optional.of(carpool));
        when(carpoolRepository.save(carpool)).thenReturn(carpool);

        carpoolService.addPassengerToCarpool(1L, passenger);

        assertEquals(1, carpool.getPassengers().size());
        verify(carpoolRepository, times(1)).save(carpool);
    }

    @Test
    void testAddPassengerToCarpool_FullCarpool() {
        Carpool carpool = new Carpool();
        carpool.setId(1L);
        carpool.setSeats(1);
        carpool.setPassengers(new ArrayList<>());
        carpool.getPassengers().add(new User());

        User passenger = new User();
        when(carpoolRepository.findById(1L)).thenReturn(Optional.of(carpool));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            carpoolService.addPassengerToCarpool(1L, passenger);
        });

        assertEquals("Carpool is already full", exception.getMessage());
        verify(carpoolRepository, never()).save(carpool);
    }
}
