package br.com.caelum.carangobom.validacao;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListaDeErrosOutputDto {

    private List<ErroDeParametroOutputDto> erros;

    public int getQuantidadeDeErros() {
        return erros.size();
    }

    public List<ErroDeParametroOutputDto> getErros() {
        return erros;
    }

    public void setErros(List<ErroDeParametroOutputDto> erros) {
        this.erros = erros;
    }
}
