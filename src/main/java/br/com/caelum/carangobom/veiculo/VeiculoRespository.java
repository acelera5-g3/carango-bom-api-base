package br.com.caelum.carangobom.veiculo;

import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.veiculo.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRespository extends JpaRepository<Veiculo, Long>{
}

