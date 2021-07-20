package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.entities.Marca;
import br.com.caelum.carangobom.repositories.MarcaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DashboardServiceTest {


    @Mock
    MarcaRepository marcaRepository;

    DashboardService service;

    @SuppressWarnings("unchecked")
    List<Marca> marcas = mock(List.class);

    @BeforeEach
    void setup() {
        openMocks(this);
        service = new DashboardService(
                marcaRepository
        );
    }

    @Test
    void deveRecuperarDashboard() {
        when(marcaRepository.findAll()).thenReturn(marcas);
        List<DashboardDto> res = service.recuperarDashboard();
        assertNotNull(res);
    }

}