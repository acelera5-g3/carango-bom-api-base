package br.com.caelum.carangobom.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@ApiModel(
        description = "Objeto que representa uma usuario"
)
public class UsuarioDto {
    @JsonProperty("id")
    @ApiModelProperty(
            notes = "ID único do usuario"
    )
    private Long id;

    @JsonProperty("email")
    @NotBlank
    @Email(message = "Deve ser um email válido")
    @ApiModelProperty(
            notes = "Email do usuario",
            example = "diego@gmail.com"
    )
    private String email;

    @JsonProperty("senha")
    @NotBlank
    @Size(min = 4, message = "Deve ter {min} ou mais caracteres.")
    @ApiModelProperty(
            notes = "Senha do usuario",
            example = "ueuJD34542"
    )
    private String senha;
}

