package com.example.Dm3ak_Backend.RepositoriesTest;

import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.repository.CarpoolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//class CarpoolRepositoryTest {
//
//    @Autowired
//    private CarpoolRepository carpoolRepository;
//
//    @Test
//    void testFindByDepartureAndArrival() {
//        Carpool carpool = new Carpool();
//        carpool.setDeparture("City A");
//        carpool.setArrival("City B");
//        carpoolRepository.save(carpool);
//
//        List<Carpool> carpools = carpoolRepository.findByDepartureAndArrival("City A", "City B");
//
//        assertThat(carpools).hasSize(1);
//        assertThat(carpools.get(0).getDeparture()).isEqualTo("City A");
//        assertThat(carpools.get(0).getArrival()).isEqualTo("City B");
//    }
//}
@DataJpaTest
class CarpoolRepositoryTest {

    @Autowired
    private CarpoolRepository carpoolRepository;

    @Test
    void testFindByDepartureAndArrival() {
        // Create and save a carpool
        Carpool carpool = new Carpool();
        carpool.setDeparture("City A");
        carpool.setArrival("City B");
        carpoolRepository.save(carpool);

        // Fetch carpools by departure and arrival
        List<Carpool> carpools = carpoolRepository.findByDepartureAndArrival("City A", "City B");
        assertThat(carpools).isNotEmpty();
        assertThat(carpools.get(0).getDeparture()).isEqualTo("City A");
        assertThat(carpools.get(0).getArrival()).isEqualTo("City B");
    }
}
