package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.entities.Marca;
import br.com.caelum.carangobom.entities.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

class MarcaMapperTest {

    MarcaMapper mapper;

    @Mock
    Veiculo veiculo = mock(Veiculo.class);

    MarcaDto dto = new MarcaDto(1L, "Teste");
    Marca entity = new Marca(1L, "Teste", Set.of(veiculo));

    @BeforeEach
    void setup() {
        openMocks(this);
        mapper = MarcaMapper.INSTANCE;
    }

    @Test
    void nullEntityToDto() {
        MarcaDto res = mapper.entityToDto(null);
        assertNull(res);
    }

    @Test
    void nullDtoToEntity() {
        Marca res = mapper.dtoToEntity(null);
        assertNull(res);
    }

    @Test
    void nullEntitiesToDtos() {
        Iterable<MarcaDto> res = mapper.entitiesToDtos(null);
        assertNull(res);
    }


    @Test
    void entityToDto() {
        MarcaDto res = mapper.entityToDto(entity);
        assertEquals(dto.getNome(), res.getNome());
    }

    @Test
    void dtoToEntity() {
        Marca res = mapper.dtoToEntity(dto);
        assertEquals(entity.getNome(), res.getNome());
    }

    @Test
    void entitiesToDtos() {
        Iterable<MarcaDto> res = mapper.entitiesToDtos(List.of(entity));
        assertNotNull(res);
    }
}