package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.controllers.MarcaController;
import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.services.MarcaService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MarcaControllerTest {

    private MarcaController marcaController;
    private UriComponentsBuilder uriBuilder;

    @Mock
    private MarcaService service;

    @BeforeEach
    public void configuraMock() {
        openMocks(this);

        marcaController = new MarcaController(service);
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
    }

    @Test
    void deveRetornarListaQuandoHouverResultados() {

        @SuppressWarnings("unchecked")
        Page<MarcaDto> marcas = mock(Page.class);

        when(service.buscarTodos(any(Pageable.class)))
            .thenReturn(marcas);

        Pageable pagination = PageRequest.of(0, 1);

        ResponseEntity<Iterable<MarcaDto>> resultado = marcaController.listarMarcas(pagination);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    void deveRetornarMarcaPeloId() {
        MarcaDto audi = new MarcaDto(1L, "Audi");

        when(service.buscar(1L))
            .thenReturn(audi);

        ResponseEntity<MarcaDto> resposta = marcaController.recuperarMarca(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornarNotFoundQuandoRecuperarMarcaComIdInexistente() throws EntityNotFoundException {
        when(service.buscar(anyLong()))
                .thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> marcaController.recuperarMarca(1L));
    }

    @Test
    void deveResponderCreatedQuandoCadastrarMarca() {
        MarcaDto nova = new MarcaDto(1L, "Ferrari");

        when(service.salvar(nova))
            .thenReturn(nova);

        ResponseEntity<MarcaDto> resposta = marcaController.cadastrarMarca(nova, uriBuilder);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    void deveAlterarNomeQuandoMarcaExistir() {
        MarcaDto dto = new MarcaDto(1L, "NOVA Audi");

        when(service.buscar(1L))
            .thenReturn(dto);

        ResponseEntity<MarcaDto> resposta = marcaController.atualizarMarca(1L, dto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveAlterarMarcaInexistente() throws EntityNotFoundException {
        MarcaDto dto = new MarcaDto(1L, "NOVA Audi");

        when(service.atualizar(anyLong(), any(MarcaDto.class))).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> marcaController.atualizarMarca(1L, dto));
    }

    @Test
    void deveDeletarMarcaExistente() {
        MarcaDto audi = new MarcaDto(1L, "Audi");

        when(service.buscar(1L))
            .thenReturn(audi);

        ResponseEntity<MarcaDto> resposta = marcaController.apagarMarca(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveDeletarMarcaInexistente() throws EntityNotFoundException {
        when(service.apagar(anyLong())).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> marcaController.apagarMarca(1L));
    }

}