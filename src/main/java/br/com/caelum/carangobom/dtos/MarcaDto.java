package br.com.caelum.carangobom.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@ApiModel(
        description = "Objeto que representa uma marca"
)
public class MarcaDto {
    @JsonProperty("id")
    @ApiModelProperty(
            notes = "ID único da marca"
    )
    private Long id;

    @JsonProperty("nome")
    @NotBlank
    @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
    @ApiModelProperty(
            notes = "Nome da marca",
            example = "Toyota"
    )
    private String nome;
}
