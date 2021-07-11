package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.mappers.MapStructMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class MarcaControllerTest {

    private MarcaController marcaController;
    private UriComponentsBuilder uriBuilder;

    @Mock
    private MarcaRepository marcaRepository;

    @Mock
    private MapStructMapper mapper;

    @BeforeEach
    public void configuraMock() {
        openMocks(this);

        marcaController = new MarcaController(mapper, marcaRepository);
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
    }

    @Test
    void deveRetornarListaQuandoHouverResultados() {
        List<Marca> marcas = List.of(
            new Marca(1L, "Audi"),
            new Marca(2L, "BMW"),
            new Marca(3L, "Fiat")
        );

        when(marcaRepository.findAll())
            .thenReturn(marcas);

        ResponseEntity<List<MarcaDto>> resultado = marcaController.lista();
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    void deveRetornarMarcaPeloId() {
        Marca audi = new Marca(1L, "Audi");

        when(marcaRepository.findById(1L))
            .thenReturn(Optional.of(audi));

        ResponseEntity<MarcaDto> resposta = marcaController.id(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornarNotFoundQuandoRecuperarMarcaComIdInexistente() {
        when(marcaRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        ResponseEntity<MarcaDto> resposta = marcaController.id(1L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveResponderCreatedQuandoCadastrarMarca() {
        MarcaDto nova = new MarcaDto(1L, "Ferrari");

        Marca marcaSalva = new Marca(1L, "Ferrari");

        when(marcaRepository.save(mapper.marcaDtoToMarca(nova)))
            .thenReturn(marcaSalva);

        ResponseEntity<MarcaDto> resposta = marcaController.cadastra(nova, uriBuilder);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    void deveAlterarNomeQuandoMarcaExistir() {
        Marca audi = new Marca(1L, "Audi");
        MarcaDto dto = new MarcaDto(1L, "NOVA Audi");

        when(marcaRepository.findById(1L))
            .thenReturn(Optional.of(audi));

        ResponseEntity<MarcaDto> resposta = marcaController.altera(1L, dto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveAlterarMarcaInexistente() {
        MarcaDto dto = new MarcaDto(1L, "NOVA Audi");

        when(marcaRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        ResponseEntity<MarcaDto> resposta = marcaController.altera(1L, dto);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveDeletarMarcaExistente() {
        Marca audi = new Marca(1L, "Audi");

        when(marcaRepository.findById(1L))
            .thenReturn(Optional.of(audi));

        ResponseEntity<MarcaDto> resposta = marcaController.deleta(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(marcaRepository).delete(audi);
    }

    @Test
    void naoDeveDeletarMarcaInexistente() {
        when(marcaRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        ResponseEntity<MarcaDto> resposta = marcaController.deleta(1L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());

        verify(marcaRepository, never()).delete(any());
    }

}