package br.com.caelum.carangobom.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ErroDeValidacaoAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {

        // Tell, Don't ask
        List<ErroDeParametroOutputDto> listaDeErros = excecao.getBindingResult().getFieldErrors().stream()
                .map(ErroDeParametroOutputDto::new)
                .collect(toList());

        // Good Citzen
        return new ListaDeErrosOutputDto(listaDeErros);
    }
}
