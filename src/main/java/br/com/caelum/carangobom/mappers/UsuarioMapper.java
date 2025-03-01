package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.UsuarioDto;
import br.com.caelum.carangobom.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    UsuarioDto entityToDto(Usuario entity);
    Usuario dtoToEntity(UsuarioDto dto);
    Iterable<UsuarioDto> entitiesToDtos(Iterable<Usuario> entities);
}
