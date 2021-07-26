package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.dtos.UsuarioDto;
import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.entities.Perfil;
import br.com.caelum.carangobom.entities.Usuario;
import br.com.caelum.carangobom.entities.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    UsuarioMapper mapper;

    @Mock
    Perfil perfil = mock(Perfil.class);

    UsuarioDto dto = new UsuarioDto(1L, "teste@teste.com", "senha");
    Usuario entity = new Usuario(1L, "teste@teste.com", "senha", List.of(perfil));

    @BeforeEach
    void setup() {
        openMocks(this);
        mapper = UsuarioMapper.INSTANCE;
    }


    @Test
    void nullEntityToDto() {
        UsuarioDto res = mapper.entityToDto(null);
        assertNull(res);
    }

    @Test
    void nullDtoToEntity() {
        Usuario res = mapper.dtoToEntity(null);
        assertNull(res);
    }

    @Test
    void nullEntitiesToDtos() {
        Iterable<UsuarioDto> res = mapper.entitiesToDtos(null);
        assertNull(res);
    }


    @Test
    void entityToDto() {
        UsuarioDto res = mapper.entityToDto(entity);
        assertEquals(dto.getEmail(), res.getEmail());
    }

    @Test
    void dtoToEntity() {
        Usuario res = mapper.dtoToEntity(dto);
        assertEquals(entity.getEmail(), res.getEmail());
    }

    @Test
    void entitiesToDtos() {
        Iterable<UsuarioDto> res = mapper.entitiesToDtos(List.of(entity));
        assertNotNull(res);
    }
}