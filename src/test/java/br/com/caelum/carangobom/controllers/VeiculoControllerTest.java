package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.controllers.VeiculoController;
import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.services.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VeiculoControllerTest {

    @Mock
    VeiculoService service;

    private UriComponentsBuilder uriBuilder;

    private VeiculoController controller;

    private VeiculoDto dto;

    @BeforeEach
    public void setup() {
        openMocks(this);

        controller = new VeiculoController(service);
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");

        dto = new VeiculoDto(1L, "Teste", 2020, 20000L, new MarcaDto(1L, "Teste"));
    }

    @Test
    void deveRetornarUmaListaDeVeiculos() {
        @SuppressWarnings("unchecked")
        Page<VeiculoDto> veiculos = mock(Page.class);

        when(service.buscarTodos(any(Pageable.class)))
                .thenReturn(veiculos);

        Pageable pagination = PageRequest.of(0, 1);

        ResponseEntity<Iterable<VeiculoDto>> resultado = controller.listarVeiculos(pagination);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    void deveRetornarUmVeiculoPeloId() {
        when(service.buscar(1L))
                .thenReturn(dto);

        ResponseEntity<VeiculoDto> resposta = controller.recuperarVeiculo(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornarNotFoundQuandoRecuperarVeiculoComIdInexistente() throws EntityNotFoundException {
        when(service.buscar(anyLong()))
                .thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> controller.recuperarVeiculo(1L));
    }

    @Test
    void deveResponderCreatedQuandoCadastrarVeiculo() {

        when(service.salvar(any(VeiculoDto.class)))
                .thenReturn(dto);

        ResponseEntity<VeiculoDto> resposta = controller.cadastrarVeiculo(dto, uriBuilder);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    void deveAlterarNomeQuandoVeiculoExistir() {

        when(service.buscar(anyLong()))
                .thenReturn(dto);

        ResponseEntity<VeiculoDto> resposta = controller.atualizarVeiculo(1L, dto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveAlterarVeiculoInexistente() throws EntityNotFoundException {

        when(service.atualizar(anyLong(), any(VeiculoDto.class))).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> controller.atualizarVeiculo(1L, dto));
    }

    @Test
    void deveDeletarVeiculoExistente() {

        when(service.buscar(anyLong()))
                .thenReturn(dto);

        ResponseEntity<VeiculoDto> resposta = controller.apagarVeiculo(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveDeletarVeiculoInexistente() throws EntityNotFoundException {
        when(service.apagar(anyLong())).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> controller.apagarVeiculo(1L));
    }

}