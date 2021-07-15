package br.com.caelum.carangobom.usuarios.mappers;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.usuarios.dtos.UsuarioDto;
import br.com.caelum.carangobom.usuarios.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UsuarioMapper {
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioDtoToUsuario(UsuarioDto usuario);

    Iterable<UsuarioDto> usuariosToUsuariosDto(Iterable<Usuario> usuario);
}
