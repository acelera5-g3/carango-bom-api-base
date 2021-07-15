package br.com.caelum.carangobom.usuarios;

import br.com.caelum.carangobom.CrudService;
import br.com.caelum.carangobom.usuarios.dtos.UsuarioDto;
import br.com.caelum.carangobom.usuarios.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

@Service
@Validated
public class UsuarioService implements CrudService<UsuarioDto> {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Autowired
    UsuarioService(
            UsuarioRepository repository,
            UsuarioMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioDto buscar(Long id) {
        return repository.findById(id)
                .map(mapper::usuarioToUsuarioDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<UsuarioDto> buscarTodos(Pageable pagination) {
        return repository.findAll(pagination).map(mapper::usuarioToUsuarioDto);
    }

    @Override
    public UsuarioDto salvar(UsuarioDto dto) {
        return mapper.usuarioToUsuarioDto(repository.save(mapper.usuarioDtoToUsuario(dto)));
    }

    @Override
    public UsuarioDto atualizar(Long id, UsuarioDto dto) throws EntityNotFoundException {
        UsuarioDto updated = this.buscar(id);
        dto.setId(updated.getId());
        return this.salvar(dto);
    }

    @Override
    public UsuarioDto apagar(Long id) throws EntityNotFoundException {
        UsuarioDto usuario = this.buscar(id);
        repository.delete(mapper.usuarioDtoToUsuario(usuario));
        return usuario;
    }
}
