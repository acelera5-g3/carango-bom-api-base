package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.entities.Veiculo;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface VeiculoMapper extends IMapper<VeiculoDto, Veiculo> {}
