package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        List<MarcaDto> marcas = List.of(
            new MarcaDto(1L, "Audi"),
            new MarcaDto(2L, "BMW"),
            new MarcaDto(3L, "Fiat")
        );

        when(service.buscarTodos())
            .thenReturn(marcas);

        ResponseEntity<Iterable<MarcaDto>> resultado = marcaController.listarMarcas();
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