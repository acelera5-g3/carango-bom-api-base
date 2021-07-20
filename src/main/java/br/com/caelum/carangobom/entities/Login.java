package br.com.caelum.carangobom.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @JsonProperty("email")
    @ApiModelProperty(
            example = "teste@teste.com"
    )
    private String email;

    @JsonProperty("senha")
    @ApiModelProperty(
            example = "senha"
    )
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
