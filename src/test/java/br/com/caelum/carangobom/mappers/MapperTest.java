package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.dtos.UsuarioDto;
import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.entities.Marca;
import br.com.caelum.carangobom.entities.Usuario;
import br.com.caelum.carangobom.entities.Veiculo;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@AllArgsConstructor
class TestMapper {
    @SuppressWarnings("rawtypes")
    IMapper instance;
    Object entity;
    Object dto;
}

class MapperTest {

    List<TestMapper> mappers;

    @BeforeEach()
    public void setup() {
        mappers = List.of(
                new TestMapper(
                        MarcaMapper.INSTANCE,
                        new Marca(1L, "TESTE", null),
                        new MarcaDto(1L, "TESTE")
                        ),
                new TestMapper(
                        UsuarioMapper.INSTANCE,
                        new Usuario(1L, "teste@teste.com", "senha", null),
                        new UsuarioDto(1L, "teste@teste.com", "senha")
                ),
                new TestMapper(
                        VeiculoMapper.INSTANCE,
                        new Veiculo(1L,"MODELO", 2021,20000L, new Marca(1L, "TESTE", null)),
                        new VeiculoDto(1L,"MODELO", 2021,20000L, new MarcaDto(1L, "TESTE"))
                ),
                new TestMapper(
                        VeiculoMapper.INSTANCE,
                        new Veiculo(1L,"MODELO", 2021,20000L, null),
                        new VeiculoDto(1L,"MODELO", 2021,20000L, null)
                )
        );

    }


    @Test
    void mapperEntityToDtoTest() {
        mappers.forEach( mapper -> {
            @SuppressWarnings("unchecked")
            Object dto = mapper.instance.entityToDto(mapper.entity);
            assertNotNull(dto);
        } );
    }

    @Test
    void mappersNullEntity() {
        mappers.forEach( mapper -> {
            @SuppressWarnings("unchecked")
            Object dto = mapper.instance.entityToDto(null);
            assertNull(dto);
        } );
    }

    @Test
    void mappersDtoToEntity() {
        mappers.forEach( mapper -> {
            @SuppressWarnings("unchecked")
            Object entity = mapper.instance.dtoToEntity(mapper.dto);
            assertNotNull(entity);
        } );
    }

    @Test
    void mapperDtoToEntityNull() {
        mappers.forEach( mapper -> {
            @SuppressWarnings("unchecked")
            Object entity = mapper.instance.dtoToEntity(null);
            assertNull(entity);
        } );
    }

    @Test
    void mappersEntitiesToDtos() {
        mappers.forEach( mapper -> {
            @SuppressWarnings("unchecked")
            List<Object> dtos = (List<Object>) mapper.instance.entitiesToDtos(List.of(mapper.entity));
            assertNotNull(dtos);
        } );
    }

    @Test
    void mapperEntitiesNull() {
        mappers.forEach( mapper -> {
            @SuppressWarnings("unchecked")
            List<Object> dtos = (List<Object>) mapper.instance.entitiesToDtos(null);
            assertNull(dtos);
        } );
    }

}