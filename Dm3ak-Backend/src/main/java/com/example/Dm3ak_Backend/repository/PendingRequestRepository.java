package com.example.Dm3ak_Backend.repository;

import com.example.Dm3ak_Backend.entity.PendingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendingRequestRepository extends JpaRepository<PendingRequest, Long> {
    Optional<PendingRequest> findByCarpool_Id(Long carpoolId);
}
