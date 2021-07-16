package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TokenServiceTest {

    TokenService service;

    @Mock
    Authentication authentication;

    Usuario usuario = new Usuario(1L, "teste@teste.com", "senha", null);


    @BeforeEach
    void setup() {
        openMocks(this);

        service = new TokenService();

        ReflectionTestUtils.setField(service, "expiration", "3600");
        ReflectionTestUtils.setField(service, "secret", "SECRET");

        when(authentication.getPrincipal()).thenReturn(usuario);

    }

    @Test
    void deveGerarUmToken() {
        String res = service.gerarToken(authentication);
        assertNotNull(res);
    }

    @Test
    void deveDizerQueOTokenEValido() {
        String token = service.gerarToken(authentication);
        boolean res =  service.isTokenValido(token);
        assertTrue(res);
    }

    @Test
    void deveDizerQueOTokenEInvalido() {
        boolean res = service.isTokenValido("INVALIDO!");
        assertFalse(res);
    }

    @Test
    void deveRetornarIdDoUsuario() {
        String token = service.gerarToken(authentication);
        Long res = service.getIdUsuario(token);
        assertEquals(res, usuario.getId());
    }
}