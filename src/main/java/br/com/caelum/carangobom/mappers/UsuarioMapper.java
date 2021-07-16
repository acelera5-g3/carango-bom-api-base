package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.UsuarioDto;
import br.com.caelum.carangobom.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface UsuarioMapper extends IMapper<UsuarioDto, Usuario> {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
}
