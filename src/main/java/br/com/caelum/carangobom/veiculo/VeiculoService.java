package br.com.caelum.carangobom.veiculo;

import br.com.caelum.carangobom.CrudService;
import br.com.caelum.carangobom.marca.MarcaRepository;
import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.mappers.MarcaMapper;
import br.com.caelum.carangobom.veiculo.dtos.VeiculoDto;
import br.com.caelum.carangobom.veiculo.mappers.VeiculoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

@Service
@Validated
public class VeiculoService implements CrudService<VeiculoDto> {

    private final VeiculoRespository repository;
    private final VeiculoMapper mapper;

    @Autowired
    VeiculoService(
            VeiculoRespository repository,
            VeiculoMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VeiculoDto buscar(Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .map(mapper::veiculoToVeiculoDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<VeiculoDto> buscarTodos(Pageable pagination) {
        return repository.findAll(pagination).map(mapper::veiculoToVeiculoDto);
    }

    @Override
    public VeiculoDto salvar(VeiculoDto dto) {
        return mapper.veiculoToVeiculoDto(repository.save(mapper.veiculoDtoToVeiculo(dto)));
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
        repository.delete(mapper.veiculoDtoToVeiculo(dto));
        return dto;
    }
}
