package br.com.caelum.carangobom.veiculo.dtos;

import br.com.caelum.carangobom.marca.dtos.MarcaDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Year;

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
    @NotBlank
    @ApiModelProperty(
            notes = "Ano do veiculo",
            example = "2020"
    )
    private Year ano;

    @JsonProperty("valor")
    @NotBlank
    @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
    @ApiModelProperty(
            notes = "Valor do veiculo",
            example = "20.000"
    )
    private Long valor;

    @JsonProperty("marca")
    @NotBlank
    @ApiModelProperty(
            notes = "Marca do veiculo",
            example = "HYUNDAI"
    )
    private MarcaDto marca;
}
