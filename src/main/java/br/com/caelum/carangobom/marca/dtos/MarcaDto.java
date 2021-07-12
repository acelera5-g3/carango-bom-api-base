package br.com.caelum.carangobom.marca.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class MarcaDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    @NotBlank
    @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
    private String nome;
}
