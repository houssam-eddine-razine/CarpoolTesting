package com.example.Dm3ak_Backend.EntityTest;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.PendingRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PendingRequestTest {

    @Test
    void testPendingRequestEntity() {
        PendingRequest pendingRequest = new PendingRequest();
        pendingRequest.setId(1L);
        Carpool carpool = new Carpool();
        pendingRequest.setCarpool(carpool);
        pendingRequest.setPassengers(new ArrayList<>());

        assertEquals(1L, pendingRequest.getId());
        assertNotNull(pendingRequest.getCarpool());
        assertNotNull(pendingRequest.getPassengers());
        assertTrue(pendingRequest.getPassengers().isEmpty());
    }
}
