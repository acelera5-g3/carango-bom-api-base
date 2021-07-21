package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.UsuarioDto;
import br.com.caelum.carangobom.mappers.UsuarioMapper;
import br.com.caelum.carangobom.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

@Service
@Validated
public class UsuarioService implements IService<UsuarioDto> {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Autowired
    public UsuarioService(
            UsuarioRepository repository,
            UsuarioMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioDto buscar(Long id) {
        return repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<UsuarioDto> buscarTodos(Pageable pagination) {
        return repository.findAll(pagination).map(mapper::entityToDto);
    }

    @Override
    public UsuarioDto salvar(UsuarioDto dto) {
        dto.setSenha(senhaEncrypt(dto.getSenha()));
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(dto)));
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
        repository.delete(mapper.dtoToEntity(usuario));
        return usuario;
    }

    private String senhaEncrypt(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
