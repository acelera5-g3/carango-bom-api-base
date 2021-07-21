package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.TokenDto;
import br.com.caelum.carangobom.entities.Login;
import br.com.caelum.carangobom.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AutenticacaoControllerTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private TokenService tokenService;

    private AutenticacaoController controller;

    Login login;

    @BeforeEach
    void setup() {
        openMocks(this);

        controller = new AutenticacaoController(
                authManager,
                tokenService
        );

        login = new Login("teste@teste.com", "senha");

        when(tokenService.gerarToken(any(Authentication.class))).thenReturn("TOKEN!");
    }

    @Test
    void deveAutenticarComSucesso() {
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);

        ResponseEntity<TokenDto> res = controller.autenticar(login);

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void deveFalharAutenticacao() {
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationExceptionMock("", new Exception()));

        ResponseEntity<TokenDto> res = controller.autenticar(login);

        assertEquals(HttpStatus.FORBIDDEN, res.getStatusCode());
    }

    @Test
    void deveValidarToken() {
        TokenDto token = mock(TokenDto.class);
        when(token.getToken()).thenReturn("TEST");
        when(tokenService.isTokenValido(anyString())).thenReturn(true);
        ResponseEntity<TokenDto> res = controller.validar(token);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void naoDeveValidarToken() {
        TokenDto token = mock(TokenDto.class);
        when(token.getToken()).thenReturn("TEST");
        when(tokenService.isTokenValido(anyString())).thenReturn(false);
        ResponseEntity<TokenDto> res = controller.validar(token);
        assertEquals(HttpStatus.FORBIDDEN, res.getStatusCode());
    }
}

class AuthenticationExceptionMock extends AuthenticationException {

    public AuthenticationExceptionMock(String msg, Throwable cause) {
        super(msg, cause);
    }
}