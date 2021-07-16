package br.com.caelum.carangobom.veiculo.dtos;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@ApiModel(
        description = "Objeto que representa um veiculo"
)
public class VeiculoDto {
    @JsonProperty("id")
    @ApiModelProperty(
            notes = "ID único do veiculo"
    )
    private Long id;

    @JsonProperty("modelo")
    @NotBlank
    @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
    @ApiModelProperty(
            notes = "Nome do veiculo",
            example = "HB20"
    )
    private String modelo;

    @JsonProperty("ano")
    @NotNull
    @ApiModelProperty(
            notes = "Ano do veiculo",
            example = "2020"
    )
    private int ano;

    @JsonProperty("valor")
    @NotNull
    @ApiModelProperty(
            notes = "Valor do veiculo",
            example = "20.000"
    )
    private Long valor;

    @JsonProperty("marca")
    @NotNull
    @ApiModelProperty(
            notes = "Id da marca do veiculo",
            example = "HYUNDAI"
    )
    private MarcaDto marca;
}
