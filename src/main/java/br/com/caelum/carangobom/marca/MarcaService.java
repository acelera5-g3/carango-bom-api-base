package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.CrudService;
import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.mappers.MarcaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

@Service
@Validated
public class MarcaService implements CrudService<MarcaDto> {

    private final MarcaRepository repository;
    private final MarcaMapper mapper;

    @Autowired
    MarcaService(
            MarcaRepository repository,
            MarcaMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MarcaDto buscar(Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .map(mapper::marcaToMarcaDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<MarcaDto> buscarTodos(Pageable pagination) {
        return repository.findAll(pagination).map(mapper::marcaToMarcaDto);
    }

    @Override
    public MarcaDto salvar(MarcaDto marca) {
        return mapper.marcaToMarcaDto(repository.save(mapper.marcaDtoToMarca(marca)));
    }

    @Override
    public MarcaDto atualizar(Long id, MarcaDto marca) throws EntityNotFoundException {
        MarcaDto updated = this.buscar(id);
        marca.setId(updated.getId());
        return this.salvar(marca);
    }

    @Override
    public MarcaDto apagar(Long id) throws EntityNotFoundException {
        // TODO: deve ser possível excluir uma marca que possui veículos cadastrados? Onde colocar essa regra?
        MarcaDto marca = this.buscar(id);
        repository.delete(mapper.marcaDtoToMarca(marca));
        return marca;
    }
}