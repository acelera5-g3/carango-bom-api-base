package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.UsuarioDto;
import br.com.caelum.carangobom.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Transactional
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    @Autowired
    public UsuarioController(
            UsuarioService service
    ) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation("Recupera uma lista de usuarios")
    public ResponseEntity<Iterable<UsuarioDto>> lista(Pageable pagination) {
        return ResponseEntity.ok().body(service.buscarTodos(pagination));
    }

    @GetMapping("/{id}")
    @ApiOperation("Recupera uma única usuario")
    public ResponseEntity<UsuarioDto> recuperarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping()
    @ApiOperation("Cadastra uma nova usuario")
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@Valid @RequestBody UsuarioDto request, UriComponentsBuilder uriBuilder) {
        UsuarioDto usuario = service.salvar(request);
        URI h = uriBuilder.path("/usuarios/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(h).body(usuario);
    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza uma usuario")
    public ResponseEntity<UsuarioDto> alterarNomeUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDto request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove uma usuario")
    public ResponseEntity<UsuarioDto> deleta(@PathVariable Long id) {
        return ResponseEntity.ok(service.apagar(id));
    }
}
