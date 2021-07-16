package br.com.caelum.carangobom.veiculo;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.entities.Marca;
import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.entities.Veiculo;
import br.com.caelum.carangobom.mappers.VeiculoMapper;
import br.com.caelum.carangobom.repositories.VeiculoRespository;
import br.com.caelum.carangobom.services.VeiculoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ContextConfiguration
class VeiculoServiceTest {

    @Mock
    private VeiculoRespository repository;

    @Mock
    private VeiculoMapper mapper;

    private VeiculoService service;

    VeiculoDto dto;
    Veiculo entity;

    @BeforeEach
    public void setup() {
        openMocks(this);

        service = new VeiculoService(
                repository,
                mapper
        );

        MarcaDto marcaDto = new MarcaDto(1L, "Teste");
        Marca marca = new Marca(1L, "Teste");

        dto = new VeiculoDto(1L, "Teste", 2021, 20000L, marcaDto);
        entity = new Veiculo(1L, "Teste", 2021, 20000L, marca);

        when(mapper.entityToDto(any(Veiculo.class))).thenReturn(dto);
        when(mapper.dtoToEntity(any(VeiculoDto.class))).thenReturn(entity);

        List<VeiculoDto> dtos = List.of(dto);

        when(mapper.entitiesToDtos(anyList())).thenReturn(dtos);
    }

    @Test
    void deveBuscarUmVeiculo() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        VeiculoDto response = service.buscar(1L);

        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void deveFalharAoBuscarUmVeiculo() throws EntityNotFoundException {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscar(1L));
    }

    @Test
    void deveRetornarUmaListaDeVeiculos() {
        @SuppressWarnings("unchecked")
        Page<Veiculo> list = mock(Page.class);

        when(repository.findAll(any(Pageable.class))).thenReturn(list);

        Pageable pagination = PageRequest.of(0,1);

        service.buscarTodos(pagination);

        verify(repository).findAll(pagination);
    }

    @Test
    void deveSalvarUmVeiculo() {
        when(repository.save(any(Veiculo.class))).thenReturn(entity);

        VeiculoDto res = service.salvar(dto);

        assertEquals(entity.getId(), res.getId());
    }

    @Test
    void deveAtualizarUmVeiculo() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(any(Veiculo.class))).thenReturn(entity);

        service.atualizar(1L, dto);

        verify(repository).save(entity);
    }

    @Test
    void deveApagarUmVeiculo() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        doNothing().when(repository).delete(any(Veiculo.class));

        service.apagar(1L);

        verify(repository).delete(entity);
    }
}