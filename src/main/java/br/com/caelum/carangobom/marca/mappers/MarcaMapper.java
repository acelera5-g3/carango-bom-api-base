package br.com.caelum.carangobom.marca.mappers;

import br.com.caelum.carangobom.IMapper;
import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface MarcaMapper extends IMapper<MarcaDto, Marca> {}
