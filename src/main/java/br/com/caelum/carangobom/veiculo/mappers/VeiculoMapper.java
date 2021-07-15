package br.com.caelum.carangobom.veiculo.mappers;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.veiculo.dtos.VeiculoDto;
import br.com.caelum.carangobom.veiculo.entities.Veiculo;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface VeiculoMapper {
    VeiculoDto veiculoToVeiculoDto(Veiculo veiculo);

    Veiculo veiculoDtoToVeiculo(VeiculoDto veiculo);

    Iterable<VeiculoDto> veiculosToVeiculosDtos(Iterable<Veiculo> veiculos);
}
