package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.entities.Usuario;
import br.com.caelum.carangobom.repositories.UsuarioRepository;
import br.com.caelum.carangobom.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AutenticacaoViaTokenFilterTest {
    @Mock
    private TokenService tokenService;

    @Mock
    private UsuarioRepository repository;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain filterChain;


    Usuario usuario;

    @Captor
    ArgumentCaptor<UsernamePasswordAuthenticationToken> captor;

    AutenticacaoViaTokenFilter autenticacao;

    @BeforeEach
    void setup() {
        openMocks(this);

        usuario = new Usuario(1L, "teste@teste.com", "senha", null);

        autenticacao = new AutenticacaoViaTokenFilter(
                tokenService,
                repository
        );

        when(tokenService.getIdUsuario(anyString())).thenReturn(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(usuario));
    }

    @Test
    void deveAutorizarTokenValido() throws ServletException, IOException {

        when(request.getHeader(anyString())).thenReturn("Bearer TOKENDETESTE");
        when(tokenService.isTokenValido(anyString())).thenReturn(true);

        autenticacao.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void naoDeveAutorizarTokenInvalido() throws ServletException, IOException {
        when(request.getHeader(anyString())).thenReturn("");
        when(tokenService.isTokenValido(anyString())).thenReturn(false);

        autenticacao.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}