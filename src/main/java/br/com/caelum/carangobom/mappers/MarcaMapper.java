package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.entities.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface MarcaMapper {
    MarcaMapper INSTANCE = Mappers.getMapper( MarcaMapper.class );
    MarcaDto entityToDto(Marca entity);
    Marca dtoToEntity(MarcaDto dto);
    Iterable<MarcaDto> entitiesToDtos(Iterable<Marca> entities);
}
