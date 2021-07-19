package br.com.caelum.carangobom.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDto {
    @JsonProperty("quantidade")
    private int quantidade;

    @JsonProperty("somatoria")
    private Long somatoria;

    @JsonProperty("nomeMarca")
    private String nomeMarca;

    @JsonProperty("idMarca")
    private Long idMarca;
}
