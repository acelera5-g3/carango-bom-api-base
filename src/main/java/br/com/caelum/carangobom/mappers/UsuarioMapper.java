package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.UsuarioDto;
import br.com.caelum.carangobom.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UsuarioMapper extends IMapper<UsuarioDto, Usuario> { }
