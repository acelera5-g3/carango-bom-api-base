package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.entities.Marca;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Long> {

    @Override
    Optional<Marca> findById(Long id);

    @Override
    List<Marca> findAll();

}
