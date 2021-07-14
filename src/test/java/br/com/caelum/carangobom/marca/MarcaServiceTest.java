package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.CarangoBomApiApplication;
import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.marca.mappers.MarcaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ContextConfiguration
class MarcaServiceTest {

    @Mock
    private MarcaRepository repository;

    @Mock
    private MarcaMapper mapper;


    private MarcaService service;


    @BeforeEach
    public void setup() {
        openMocks(this);

        service = new MarcaService(
                repository,
                mapper
        );

        MarcaDto dto = new MarcaDto(1L, "TESTE");
        Marca entity = new Marca(1L, "TESTE");

        when(mapper.marcaToMarcaDto(any(Marca.class))).thenReturn(dto);
        when(mapper.marcaDtoToMarca(any(MarcaDto.class))).thenReturn(entity);

        List<MarcaDto> dtos = List.of(new MarcaDto(1L, "TESTE"));

        when(mapper.marcasToMarcasDtos(anyList())).thenReturn(dtos);
    }

    @Test
    void deveBuscarUmaMarca() {
        Marca entity = new Marca(1L, "TESTE");

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        MarcaDto response = service.buscar(1L);

        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void deveFalharAoBuscarUmaMarca() throws EntityNotFoundException {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscar(1L));
    }

    @Test
    void deveBuscarTodasAsMarcas() {
        List<Marca> list = List.of(new Marca(1L, "TESTE"));

        when(repository.findAll()).thenReturn(list);

        Iterable<MarcaDto> res = service.buscarTodos();

        assertEquals(((List<MarcaDto>) res).get(0).getId(), list.get(0).getId());
    }

    @Test
    void deveSalvarUmaMarca() {
        Marca entity = new Marca(1L, "TESTE");
        when(repository.save(any(Marca.class))).thenReturn(entity);

        assertEquals(entity.getId(), service.salvar(new MarcaDto(1L, "TESTE")).getId());
    }

    @Test
    void deveAtualizarUmaMarca() {
        Marca marca = new Marca(1L, "TESTE");
        MarcaDto dto = new MarcaDto(1L, "TESTE");

        when(repository.findById(anyLong())).thenReturn(Optional.of(marca));
        when(repository.save(any(Marca.class))).thenReturn(marca);

        service.atualizar(1L, dto);

        verify(repository, times(1)).save(marca);
    }

    @Test
    void deveDeletarUmaMarca() {
        Marca marca = new Marca(1L, "TESTE");

        when(repository.findById(anyLong())).thenReturn(Optional.of(marca));

        doNothing().when(repository).delete(any(Marca.class));

        service.deleta(1L);

        verify(repository, times(1)).delete(marca);

    }
}