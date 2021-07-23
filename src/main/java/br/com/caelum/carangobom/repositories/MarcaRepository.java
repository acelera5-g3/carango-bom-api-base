package br.com.caelum.carangobom.repositories;

import br.com.caelum.carangobom.entities.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MarcaRepository extends Repository<Marca, Long> {
    Marca save(Marca marca);

    void delete(Marca marca);

    Optional<Marca> findById(Long id);

    Page<Marca> findAll(Pageable pageable);

    Page<Marca> findAll();
}
