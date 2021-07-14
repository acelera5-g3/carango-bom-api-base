package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {}
