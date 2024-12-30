package com.example.Dm3ak_Backend.service;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.PendingRequest;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.repository.PendingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PendingRequestService {

    @Autowired
    private PendingRequestRepository pendingRequestRepository;

    public PendingRequest addPendingRequest(Carpool carpool, User passenger) {
        PendingRequest pendingRequest = pendingRequestRepository.findByCarpool_Id(carpool.getId())
                .orElse(new PendingRequest());
        pendingRequest.setCarpool(carpool);

        if (pendingRequest.getPassengers() == null) {
            pendingRequest.setPassengers(new ArrayList<>()); // Ensure passengers list is initialized
        }

        pendingRequest.getPassengers().add(passenger);
        return pendingRequestRepository.save(pendingRequest);
    }
}
