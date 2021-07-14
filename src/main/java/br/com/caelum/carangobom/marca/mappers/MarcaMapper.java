package br.com.caelum.carangobom.marca.mappers;

import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

@Mapper(
        componentModel = "spring"
)
public interface MarcaMapper {
    MarcaDto marcaToMarcaDto(Marca marca);

    Marca marcaDtoToMarca(MarcaDto marca);

    Iterable<MarcaDto> marcasToMarcasDtos(Iterable<Marca> marcas);
}
