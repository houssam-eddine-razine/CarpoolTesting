package com.example.Dm3ak_Backend.ServicesTest;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.PendingRequest;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.repository.PendingRequestRepository;
import com.example.Dm3ak_Backend.service.PendingRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class PendingRequestServiceTest {
    @InjectMocks
    private PendingRequestService pendingRequestService;

    @Mock
    private PendingRequestRepository pendingRequestRepository;

    public PendingRequestServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPendingRequest() {
        Carpool carpool = new Carpool();
        User passenger = new User();
        PendingRequest pendingRequest = new PendingRequest();
        when(pendingRequestRepository.findById(carpool.getId())).thenReturn(Optional.empty());
        when(pendingRequestRepository.save(any(PendingRequest.class))).thenReturn(pendingRequest);

        PendingRequest result = pendingRequestService.addPendingRequest(carpool, passenger);

        assertNotNull(result);
        verify(pendingRequestRepository, times(1)).save(any(PendingRequest.class));
    }
}

