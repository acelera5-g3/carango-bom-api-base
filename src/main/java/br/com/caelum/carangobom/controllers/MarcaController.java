package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.MarcaDto;
import br.com.caelum.carangobom.services.MarcaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Transactional
@RequestMapping("/marcas")
public class MarcaController {
    private final MarcaService service;

    @Autowired
    public MarcaController(
            MarcaService service
    ) {
        this.service = service;
    }

    @GetMapping()
    @Cacheable(value = "marcas")
    @ApiOperation("Recupera uma lista de marcas")
    public ResponseEntity<Iterable<MarcaDto>> listarMarcas(
            Pageable pagination
    ) {
        return ResponseEntity.ok().body(service.buscarTodos(pagination));
    }

    @GetMapping("/{id}")
    @Cacheable(value = "marcas")
    @ApiOperation("Recupera uma única marca")
    public ResponseEntity<MarcaDto> recuperarMarca(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping()
    @CacheEvict(value = "marcas", allEntries = true)
    @ApiOperation("Cadastra uma nova marca")
    public ResponseEntity<MarcaDto> cadastrarMarca(@Valid @RequestBody MarcaDto request, UriComponentsBuilder uriBuilder) {
        MarcaDto marca = service.salvar(request);
        URI h = uriBuilder.path("/marcas/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(h).body(marca);
    }

    @PutMapping("{id}")
    @CacheEvict(value = "marcas", allEntries = true)
    @ApiOperation("Atualiza uma marca")
    public ResponseEntity<MarcaDto> atualizarMarca(@PathVariable Long id, @Valid @RequestBody MarcaDto request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "marcas", allEntries = true)
    @ApiOperation("Remove uma marca")
    public ResponseEntity<MarcaDto> apagarMarca(@PathVariable Long id) {
        return ResponseEntity.ok(service.apagar(id));
    }
}