package com.example.Dm3ak_Backend.RepositoriesTest;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.PendingRequest;
import com.example.Dm3ak_Backend.repository.CarpoolRepository;
import com.example.Dm3ak_Backend.repository.PendingRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PendingRequestRepositoryTest {

    @Autowired
    private PendingRequestRepository pendingRequestRepository;

    @Autowired
    private CarpoolRepository carpoolRepository; // Ensure this is included

    @Test
    void testFindByCarpoolId() {
        // Persist Carpool
        Carpool carpool = new Carpool();
        carpool.setDeparture("City A");
        carpool.setArrival("City B");
        carpool = carpoolRepository.save(carpool); // Save Carpool

        // Persist PendingRequest
        PendingRequest pendingRequest = new PendingRequest();
        pendingRequest.setCarpool(carpool);
        pendingRequestRepository.save(pendingRequest);

        // Validate
        Optional<PendingRequest> result = pendingRequestRepository.findByCarpool_Id(carpool.getId());
        assertTrue(result.isPresent());
        assertEquals(carpool.getId(), result.get().getCarpool().getId());
    }
}
