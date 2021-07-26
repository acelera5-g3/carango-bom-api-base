package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.entities.Marca;
import br.com.caelum.carangobom.entities.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

class VeiculoMapperTest {

    VeiculoMapper mapper;

    @Mock
    MarcaDto marcaDto = mock(MarcaDto.class);

    @Mock
    Marca marca = mock(Marca.class);

    VeiculoDto dto;
    Veiculo entity;

    @BeforeEach
    void setup() {
        openMocks(this);
        dto = new VeiculoDto(1L, "Teste", 2020, 20000L, marcaDto);
        entity = new Veiculo(1L, "Teste", 2020, 20000L, marca);
        mapper = VeiculoMapper.INSTANCE;
    }

    @Test
    void nullEntityToDto() {
        VeiculoDto res = mapper.entityToDto(null);
        assertNull(res);
    }

    @Test
    void nullDtoToEntity() {
        Veiculo res = mapper.dtoToEntity(null);
        assertNull(res);
    }

    @Test
    void nullEntitiesToDtos() {
        Iterable<VeiculoDto> res = mapper.entitiesToDtos(null);
        assertNull(res);
    }

    @Test
    void entityToDto() {
        VeiculoDto res = mapper.entityToDto(entity);
        assertEquals(dto.getModelo(), res.getModelo());
    }

    @Test
    void entityToDtoWithNullMarca() {
        entity.setMarca(null);
        VeiculoDto res = mapper.entityToDto(entity);
        assertEquals(dto.getModelo(), res.getModelo());
    }

    @Test
    void dtoToEntity() {
        Veiculo res = mapper.dtoToEntity(dto);
        assertEquals(entity.getModelo(), res.getModelo());
    }

    @Test
    void dtoToEntityWithNullMarca() {
        dto.setMarca(null);
        Veiculo res = mapper.dtoToEntity(dto);
        assertEquals(entity.getModelo(), res.getModelo());
    }

    @Test
    void entitiesToDtos() {
        Iterable<VeiculoDto> res = mapper.entitiesToDtos(List.of(entity));
        assertNotNull(res);
    }

    @Test
    void entitiesToDtosWithNullMarcas() {
        entity.setMarca(null);
        Iterable<VeiculoDto> res = mapper.entitiesToDtos(List.of(entity));
        assertNotNull(res);
    }
}