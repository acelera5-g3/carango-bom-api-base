package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.entities.Marca;
import br.com.caelum.carangobom.entities.Veiculo;
import br.com.caelum.carangobom.repositories.MarcaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DashboardServiceTest {


    @Mock
    MarcaRepository marcaRepository;

    @Mock
    Pageable paginacao = mock(Pageable.class);


    DashboardService service;
    List<Marca> marcas = List.of(mock(Marca.class));

    Page<Marca> pages = new PageImpl<>(marcas);

    @BeforeEach
    void setup() {
        openMocks(this);
        service = new DashboardService(
                marcaRepository
        );
    }

    @Test
    void deveRecuperarDashboard() {
        when(marcaRepository.findAll(any(Pageable.class))).thenReturn(pages);
        Page<DashboardDto> res = service.recuperarDashboard(paginacao);
        assertNotNull(res);
    }

}