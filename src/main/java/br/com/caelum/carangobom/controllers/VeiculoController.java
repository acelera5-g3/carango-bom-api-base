package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.VeiculoDto;
import br.com.caelum.carangobom.services.VeiculoService;
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
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService service;

    @Autowired
    public VeiculoController(
            VeiculoService service
    ) {
        this.service = service;
    }

    @GetMapping()
    @Cacheable(value = "veiculos")
    @ApiOperation("Recupera uma lista de veículos")
    public ResponseEntity<Iterable<VeiculoDto>> listarVeiculos(
            Pageable pagination
    ) {
        return ResponseEntity.ok().body(service.buscarTodos(pagination));
    }

    @GetMapping("/{id}")
    @Cacheable(value = "veiculos")
    @ApiOperation("Recupera um único veículo")
    public ResponseEntity<VeiculoDto> recuperarVeiculo(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping()
    @CacheEvict(value = "veiculos", allEntries = true)
    @ApiOperation("Cadastra um novo veículo")
    public ResponseEntity<VeiculoDto> cadastrarVeiculo(@Valid @RequestBody VeiculoDto request, UriComponentsBuilder uriBuilder) {
        VeiculoDto veiculo = service.salvar(request);
        URI h = uriBuilder.path("/veiculos/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(h).body(veiculo);
    }

    @PutMapping("{id}")
    @CacheEvict(value = "veiculos", allEntries = true)
    @ApiOperation("Atualiza um veículo")
    public ResponseEntity<VeiculoDto> atualizarVeiculo(@PathVariable Long id, @Valid @RequestBody VeiculoDto request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "veiculos", allEntries = true)
    @ApiOperation("Remove um veiculo")
    public ResponseEntity<VeiculoDto> apagarVeiculo(@PathVariable Long id) {
        return ResponseEntity.ok(service.apagar(id));
    }
}
