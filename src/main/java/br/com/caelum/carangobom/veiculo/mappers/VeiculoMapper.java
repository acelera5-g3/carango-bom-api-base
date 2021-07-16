package br.com.caelum.carangobom.veiculo.mappers;

import br.com.caelum.carangobom.IMapper;
import br.com.caelum.carangobom.veiculo.dtos.VeiculoDto;
import br.com.caelum.carangobom.veiculo.entities.Veiculo;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface VeiculoMapper extends IMapper<VeiculoDto, Veiculo> {}
