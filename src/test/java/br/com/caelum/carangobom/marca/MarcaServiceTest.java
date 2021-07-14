package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.marca.mappers.MarcaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        @SuppressWarnings("unchecked")
        Page<Marca> list = mock(Page.class);

        when(repository.findAll(any(Pageable.class))).thenReturn(list);

        Pageable pagination = PageRequest.of(0,1);

        service.buscarTodos(pagination);

        verify(repository).findAll(pagination);
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

        service.apagar(1L);

        verify(repository, times(1)).delete(marca);

    }
}