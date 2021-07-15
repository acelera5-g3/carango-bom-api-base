package br.com.caelum.carangobom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public interface CrudService<T> {

    T buscar(Long id);

    Page<T> buscarTodos(Pageable pagination);

    T salvar(T dto);

    T atualizar(Long id, T dto) throws EntityNotFoundException;

    T apagar(Long id) throws EntityNotFoundException;
}
