package br.com.caelum.carangobom.usuarios;

import br.com.caelum.carangobom.usuarios.dtos.UsuarioDto;
import br.com.caelum.carangobom.usuarios.entities.Usuario;
import br.com.caelum.carangobom.usuarios.mappers.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @Mock
    UsuarioMapper mapper;

    UsuarioService service;

    UsuarioDto dto;
    Usuario entity;

    @BeforeEach
    public void setup() {
        openMocks(this);

        service = new UsuarioService(
                repository,
                mapper
        );

        dto = new UsuarioDto(1L, "teste@teste.com", "s3nh4");
        entity = new Usuario(1L, "teste@teste.com", "s3nh4", null);

        List<UsuarioDto> dtos = List.of(dto);

        when(mapper.usuarioToUsuarioDto(any(Usuario.class))).thenReturn(dto);
        when(mapper.usuarioDtoToUsuario(any(UsuarioDto.class))).thenReturn(entity);
        when(mapper.usuariosToUsuariosDto(anyList())).thenReturn(dtos);
    }

    @Test
    void deveBuscarUmUsuario() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        UsuarioDto resposta = service.buscar(1L);
        assertEquals(entity.getId(), resposta.getId());
    }

    @Test
    void deveFalharAoBuscarUmUsuario() throws EntityNotFoundException {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.buscar(1L));
    }

    @Test
    void deveBuscarTodosOsUsuarios() {
        @SuppressWarnings("unchecked")
        Page<Usuario> list = mock(Page.class);

        when(repository.findAll(any(Pageable.class))).thenReturn(list);

        Pageable pagination = PageRequest.of(0,1);

        service.buscarTodos(pagination);

        verify(repository).findAll(pagination);
    }

    @Test
    void deveSalvarUmUsuario() {
        when(repository.save(any(Usuario.class))).thenReturn(entity);

        UsuarioDto res = service.salvar(dto);

        assertEquals(entity.getId(), res.getId());
    }

    @Test
    void deveAtualizarUmUsuario() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(any(Usuario.class))).thenReturn(entity);

        service.atualizar(1L, dto);

        verify(repository).save(entity);
    }

    @Test
    void deveApagarUmUsuario() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        doNothing().when(repository).delete(any(Usuario.class));

        service.apagar(1L);

        verify(repository).delete(entity);
    }
}