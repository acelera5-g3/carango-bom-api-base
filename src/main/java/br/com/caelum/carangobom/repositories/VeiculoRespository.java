package br.com.caelum.carangobom.repositories;

import br.com.caelum.carangobom.entities.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface VeiculoRespository extends Repository<Veiculo, Long> {
    Veiculo save(Veiculo marca);

    void delete(Veiculo marca);

    Optional<Veiculo> findById(Long id);

    Page<Veiculo> findAll(Pageable pageable);
}

