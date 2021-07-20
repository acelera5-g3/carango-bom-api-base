package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.services.DashboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class DashboardControllerTest {

    @Mock
    DashboardService service;
    DashboardController controller;

    List<DashboardDto> dto;

    @BeforeEach
    void setup() {
        openMocks(this);
        controller = new DashboardController(
                service
        );

        dto = List.of(new DashboardDto(1,100L,"MARCA", 1L));
    }

    @Test
    void deveRecuperarADashboard() {
        when(service.recuperarDashboard()).thenReturn(dto);
        ResponseEntity<List<DashboardDto>> res = controller.dashboard();

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

}
