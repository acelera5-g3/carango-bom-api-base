package br.com.caelum.carangobom.mappers;

import org.mapstruct.factory.Mappers;

public interface IMapper<D,E> {
    D entityToDto(E entity);
    E dtoToEntity(D dto);
    Iterable<D> entitiesToDtos(Iterable<E> entities);
}
