package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.entities.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface VeiculoMapper extends IMapper<VeiculoDto, Veiculo> {
    VeiculoMapper INSTANCE = Mappers.getMapper(VeiculoMapper.class);
}
