package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.entities.Marca;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Long> {}
