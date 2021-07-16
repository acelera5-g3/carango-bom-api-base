package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.mappers.VeiculoMapper;
import br.com.caelum.carangobom.repositories.VeiculoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

@Service
@Validated
public class VeiculoService implements IService<VeiculoDto> {

    private final VeiculoRespository repository;
    private final VeiculoMapper mapper;

    @Autowired
    public VeiculoService(
            VeiculoRespository repository,
            VeiculoMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VeiculoDto buscar(Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<VeiculoDto> buscarTodos(Pageable pagination) {
        return repository.findAll(pagination).map(mapper::entityToDto);
    }

    @Override
    public VeiculoDto salvar(VeiculoDto dto) {
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(dto)));
    }

    @Override
    public VeiculoDto atualizar(Long id, VeiculoDto dto) throws EntityNotFoundException {
        VeiculoDto updated = this.buscar(id);
        dto.setId(updated.getId());
        return this.salvar(dto);
    }

    @Override
    public VeiculoDto apagar(Long id) throws EntityNotFoundException {
        VeiculoDto dto = this.buscar(id);
        repository.delete(mapper.dtoToEntity(dto));
        return dto;
    }
}
