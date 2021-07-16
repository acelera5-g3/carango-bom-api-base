package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.entities.Marca;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface MarcaMapper extends IMapper<MarcaDto, Marca> {}
