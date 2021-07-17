package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.dtos.VeiculoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DashboardServiceTest {


    @Mock
    VeiculoService veiculoService;

    DashboardService service;

    @SuppressWarnings("unchecked")
    Page<VeiculoDto> veiculos = mock(Page.class);

    @BeforeEach
    void setup() {
        openMocks(this);
        service = new DashboardService(
                veiculoService
        );
    }

    @Test
    void deveRecuperarDashboard() {
        when(veiculoService.buscarTodos(any(Pageable.class))).thenReturn(veiculos);
        List<DashboardDto> res = service.recuperarDashboard();
        assertNotNull(res);
    }

}