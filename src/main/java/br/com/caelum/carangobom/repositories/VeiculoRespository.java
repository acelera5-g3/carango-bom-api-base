package br.com.caelum.carangobom.repositories;

import br.com.caelum.carangobom.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRespository extends JpaRepository<Veiculo, Long>{
}

