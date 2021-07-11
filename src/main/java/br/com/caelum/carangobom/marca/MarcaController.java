package br.com.caelum.carangobom.marca;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import br.com.caelum.carangobom.marca.entities.Marca;
import br.com.caelum.carangobom.mappers.MapStructMapper;
import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
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

    @GetMapping("/marcas")
    @ResponseBody
    @Transactional
    public ResponseEntity<List<MarcaDto>> lista() {
        return ResponseEntity.ok().body(mapper.marcasToMarcasDtos(repository.findAll()));
    }

    @GetMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<MarcaDto> id(@PathVariable Long id) {
        Optional<Marca> m1 = repository.findById(id);
        return m1.map(marca -> ResponseEntity.ok(mapper.marcaToMarcaDto(marca))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/marcas")
    @ResponseBody
    @Transactional
    public ResponseEntity<MarcaDto> cadastra(@Valid @RequestBody MarcaDto request, UriComponentsBuilder uriBuilder) {
        Marca marca = repository.save(mapper.marcaDtoToMarca(request));
        URI h = uriBuilder.path("/marcas/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(h).body(mapper.marcaToMarcaDto(marca));
    }

    @PutMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<MarcaDto> altera(@PathVariable Long id, @Valid @RequestBody MarcaDto request) {
        Optional<Marca> marca = repository.findById(id);
        if (marca.isPresent()) {
            marca.get().setNome(request.getNome());
            return ResponseEntity.ok(mapper.marcaToMarcaDto(marca.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<MarcaDto> deleta(@PathVariable Long id) {
        Optional<Marca> marca = repository.findById(id);
        if (marca.isPresent()) {
            repository.delete(marca.get());
            return ResponseEntity.ok(mapper.marcaToMarcaDto(marca.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {
        List<ErroDeParametroOutputDto> l = new ArrayList<>();
        excecao.getBindingResult().getFieldErrors().forEach(e -> {
            ErroDeParametroOutputDto d = new ErroDeParametroOutputDto();
            d.setParametro(e.getField());
            d.setMensagem(e.getDefaultMessage());
            l.add(d);
        });
        ListaDeErrosOutputDto l2 = new ListaDeErrosOutputDto();
        l2.setErros(l);
        return l2;
    }
}