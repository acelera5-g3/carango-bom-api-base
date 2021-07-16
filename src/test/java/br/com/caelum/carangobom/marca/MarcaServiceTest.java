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

    MarcaDto dto;
    Marca entity;


    @BeforeEach
    public void setup() {
        openMocks(this);

        service = new MarcaService(
                repository,
                mapper
        );

        dto = new MarcaDto(1L, "TESTE");
        entity = new Marca(1L, "TESTE");

        when(mapper.entityToDto(any(Marca.class))).thenReturn(dto);
        when(mapper.dtoToEntity(any(MarcaDto.class))).thenReturn(entity);

        List<MarcaDto> dtos = List.of(new MarcaDto(1L, "TESTE"));

        when(mapper.entitiesToDtos(anyList())).thenReturn(dtos);
    }

    @Test
    void deveBuscarUmaMarca() {
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
        when(repository.save(any(Marca.class))).thenReturn(entity);

        MarcaDto res = service.salvar(dto);

        assertEquals(entity.getId(), res.getId());
    }

    @Test
    void deveAtualizarUmaMarca() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(any(Marca.class))).thenReturn(entity);

        service.atualizar(1L, dto);

        verify(repository).save(entity);
    }

    @Test
    void deveApagarUmaMarca() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        doNothing().when(repository).delete(any(Marca.class));

        service.apagar(1L);

        verify(repository).delete(entity);

    }
}