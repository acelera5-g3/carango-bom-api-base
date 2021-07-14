package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation("Recupera uma lista de marcas")
    public ResponseEntity<Iterable<MarcaDto>> lista() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation("Recupera uma única marca")
    public ResponseEntity<MarcaDto> recuperarMarca(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping()
    @ApiOperation("Cadastra uma nova marca")
    public ResponseEntity<MarcaDto> cadastrarMarca(@Valid @RequestBody MarcaDto request, UriComponentsBuilder uriBuilder) {
        MarcaDto marca = service.salvar(request);
        // TODO: Ainda não entendi qualé a desse uriBuilder
        URI h = uriBuilder.path("/marcas/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(h).body(marca);
    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza uma marca")
    public ResponseEntity<MarcaDto> alterarNomeMarca(@PathVariable Long id, @Valid @RequestBody MarcaDto request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove uma marca")
    public ResponseEntity<MarcaDto> deleta(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleta(id));
    }
}