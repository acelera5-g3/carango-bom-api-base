package br.com.caelum.carangobom;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public interface CrudService<T> {

    T buscar(Long id);

    Iterable<T> buscarTodos();

    T salvar(T dto);

    T atualizar(Long id, T dto) throws EntityNotFoundException;

    T apagar(Long id) throws EntityNotFoundException;
}
