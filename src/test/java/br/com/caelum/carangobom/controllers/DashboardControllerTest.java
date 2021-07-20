package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.services.DashboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DashboardControllerTest {

    @Mock
    DashboardService service;

    DashboardController controller;

    @Mock
    Pageable paginacao = mock(Pageable.class);

    @SuppressWarnings("unchecked")
    Page<DashboardDto> dashboard = mock(Page.class);

    @BeforeEach
    void setup() {
        openMocks(this);
        controller = new DashboardController(
                service
        );
    }

    @Test
    void deveRecuperarADashboard() {
        when(service.recuperarDashboard(any(Pageable.class))).thenReturn(dashboard);
        ResponseEntity<Page<DashboardDto>> res = controller.dashboard(paginacao);

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

}
