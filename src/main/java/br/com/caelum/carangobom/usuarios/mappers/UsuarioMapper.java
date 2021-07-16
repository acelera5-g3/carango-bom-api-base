package br.com.caelum.carangobom.usuarios.mappers;

import br.com.caelum.carangobom.IMapper;
import br.com.caelum.carangobom.usuarios.dtos.UsuarioDto;
import br.com.caelum.carangobom.usuarios.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UsuarioMapper extends IMapper<UsuarioDto, Usuario> { }
