package com.example.Dm3ak_Backend.ControllersTest;

import com.example.Dm3ak_Backend.controller.CarpoolController;
import com.example.Dm3ak_Backend.entity.Carpool;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.service.CarpoolService;
import com.example.Dm3ak_Backend.service.PendingRequestService;
import com.example.Dm3ak_Backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CarpoolControllerTest {

    private MockMvc mockMvc;
    private CarpoolService carpoolService;
    private PendingRequestService pendingRequestService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        carpoolService = Mockito.mock(CarpoolService.class);
        pendingRequestService = Mockito.mock(PendingRequestService.class);
        userService = Mockito.mock(UserService.class);
        CarpoolController carpoolController = new CarpoolController(carpoolService, pendingRequestService, userService);
        mockMvc = MockMvcBuilders.standaloneSetup(carpoolController).build();
    }

    @Test
    void testGetAllCarpools() throws Exception {
        when(carpoolService.getAllCarpools()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/carpools"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCarpool() throws Exception {
        Carpool carpool = new Carpool();
        carpool.setId(1L);

        when(userService.getUser(1L)).thenReturn(new User());
        when(carpoolService.saveCarpool(any(Carpool.class))).thenReturn(carpool);

        mockMvc.perform(post("/api/carpools")
                        .param("driverId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departure\":\"City A\",\"arrival\":\"City B\"}"))
                .andExpect(status().isOk());
    }

  @Test
    void testDeleteCarpool() throws Exception {
        Mockito.doNothing().when(carpoolService).deleteCarpool(1L);

        mockMvc.perform(delete("/api/carpools/1"))
                .andExpect(status().isOk());
    }

}

