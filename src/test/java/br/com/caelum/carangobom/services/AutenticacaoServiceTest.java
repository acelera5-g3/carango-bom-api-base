package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.entities.Usuario;
import br.com.caelum.carangobom.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AutenticacaoServiceTest {

    @Mock
    UsuarioRepository repository;

    AutenticacaoService service;

    Usuario usuario = new Usuario(1L, "teste@teste.com", "senha", null);

    @BeforeEach
    void setup() {
        openMocks(this);

        service = new AutenticacaoService(
                repository
        );
    }

    @Test
    void deveEncontarOUsuario() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(usuario));
        UserDetails res = service.loadUserByUsername("teste@teste.com");
        assertEquals(usuario.getEmail(), res.getUsername());
    }

    @Test
    void deveFalharAoEncontrarOUsuario() throws UsernameNotFoundException {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("teste@teste.com"));
    }

}