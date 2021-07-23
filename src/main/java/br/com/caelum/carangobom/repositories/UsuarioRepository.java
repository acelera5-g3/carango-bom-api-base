package br.com.caelum.carangobom.repositories;

import br.com.caelum.carangobom.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UsuarioRepository extends Repository<Usuario, Long> {

    Usuario save(Usuario marca);

    void delete(Usuario marca);

    Optional<Usuario> findById(Long id);

    Page<Usuario> findAll(Pageable pageable);

    Optional<Usuario> findByEmail(String email);
}
