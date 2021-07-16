package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.DashboardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashboardServiceTest {


    @Mock
    VeiculoService veiculoService;

    DashboardService service;

    @BeforeEach
    void setup() {
        service = new DashboardService(
                veiculoService
        );
    }

    @Test
    void deveRecuperarDashboard() {
        List<DashboardDto> res = service.recuperarDashboard();
        assertNotNull(res);
    }

}