package br.com.caelum.carangobom.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
public class ErroDeParametroOutputDto {

    private String parametro;
    private String mensagem;

    public ErroDeParametroOutputDto(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
