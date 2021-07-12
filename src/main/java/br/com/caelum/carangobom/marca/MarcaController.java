package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.mappers.MapStructMapper;
import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/marcas")
public class MarcaController {

    private final MapStructMapper mapper;
    private final MarcaRepository repository;

    @Autowired
    public MarcaController(
            MapStructMapper mapper,
            MarcaRepository repository
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @GetMapping()
    public ResponseEntity<List<MarcaDto>> lista() {
        return ResponseEntity.ok().body(mapper.marcasToMarcasDtos(repository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> recuperarMarca(@PathVariable Long id) {
        Optional<Marca> m1 = repository.findById(id);
        return m1.map(marca -> ResponseEntity.ok(mapper.marcaToMarcaDto(marca))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<MarcaDto> cadastrarMarca(@Valid @RequestBody MarcaDto request, UriComponentsBuilder uriBuilder) {
        Marca marca = repository.save(mapper.marcaDtoToMarca(request));
        URI h = uriBuilder.path("/marcas/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(h).body(mapper.marcaToMarcaDto(marca));
    }

    @PutMapping("{id}")
    public ResponseEntity<MarcaDto> alterarNomeMarca(@PathVariable Long id, @Valid @RequestBody MarcaDto request) {
        Optional<Marca> marca = repository.findById(id);
        if (marca.isPresent()) {
            marca.get().setNome(request.getNome());
            return ResponseEntity.ok(mapper.marcaToMarcaDto(marca.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MarcaDto> deleta(@PathVariable Long id) {
        Optional<Marca> marca = repository.findById(id);
        if (marca.isPresent()) {
            repository.delete(marca.get());
            return ResponseEntity.ok(mapper.marcaToMarcaDto(marca.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}