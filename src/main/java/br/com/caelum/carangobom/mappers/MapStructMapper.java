package br.com.caelum.carangobom.mappers;

import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    MarcaDto marcaToMarcaDto(Marca marca);

    Marca marcaDtoToMarca(MarcaDto marca);

    List<MarcaDto> marcasToMarcasDtos(List<Marca> marcas);
}
