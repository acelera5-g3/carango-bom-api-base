package br.com.caelum.carangobom.marca.mappers;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface MarcaMapper {
    MarcaDto marcaToMarcaDto(Marca marca);

    Marca marcaDtoToMarca(MarcaDto marca);

    Iterable<MarcaDto> marcasToMarcasDtos(Iterable<Marca> marcas);
}
