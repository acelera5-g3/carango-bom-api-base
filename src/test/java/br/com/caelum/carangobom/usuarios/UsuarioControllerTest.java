package br.com.caelum.carangobom.usuarios;

import br.com.caelum.carangobom.usuarios.dtos.UsuarioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UsuarioControllerTest {

    private UsuarioController usuarioController;
    private UriComponentsBuilder uriBuilder;

    private UsuarioDto dto;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void configuraMock() {
        openMocks(this);

        dto = new UsuarioDto(1L, "Ary", "123456");


        usuarioController = new UsuarioController(usuarioService);
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
    }

    @Test
    void deveRetornarUmaListaDeUsuarios() {
        @SuppressWarnings("unchecked")
        Page<UsuarioDto> usuarios = mock(Page.class);

        when(usuarioService.buscarTodos(any(Pageable.class)))
                .thenReturn(usuarios);

        Pageable pagination = PageRequest.of(0, 1);

        ResponseEntity<Iterable<UsuarioDto>> resultado = usuarioController.lista(pagination);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    void deveRetornarUmUsuario() {
        when(usuarioService.buscar(1L))
                .thenReturn(dto);

        ResponseEntity<UsuarioDto> resposta = usuarioController.recuperarUsuario(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveFalharAoRetornarUsuario(){
        when(usuarioService.buscar(anyLong()))
                .thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> usuarioController.recuperarUsuario(1L));
    }

    @Test
    void deveResponderCreatedQuandoCadastrarUmUsuario() {
        when(usuarioService.salvar(any(UsuarioDto.class)))
                .thenReturn(dto);

        ResponseEntity<UsuarioDto> resposta = usuarioController.cadastrarUsuario(dto, uriBuilder);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    void deveAlterarSenhaDoUsuarioExistente() {
        when(usuarioService.buscar(anyLong()))
                .thenReturn(dto);

        ResponseEntity<UsuarioDto> resposta = usuarioController.alterarNomeUsuario(1L, dto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveDeletarUmUsuarioExistente() {
        when(usuarioService.apagar(anyLong()))
                .thenReturn(dto);

        ResponseEntity<UsuarioDto> resposta = usuarioController.deleta(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
}