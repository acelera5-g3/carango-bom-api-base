package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.entities.Marca;
import br.com.caelum.carangobom.entities.Veiculo;
import br.com.caelum.carangobom.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class DashboardService {

    private MarcaRepository marcaRepository;


    @Autowired
    DashboardService(
            MarcaRepository marcaRepository
    ) {
        this.marcaRepository = marcaRepository;
    }

    private DashboardDto pegarInformacoesDaMarca(Marca marca){
        DashboardDto informacoes = new DashboardDto();

        informacoes.setIdMarca(marca.getId());
        informacoes.setNomeMarca(marca.getNome());
        informacoes.setQuantidade(marca.getVeiculos().size());

        informacoes.setSomatoria(marca.getVeiculos().stream().mapToLong(Veiculo::getValor).sum());
        return informacoes;
    }

    public Page<DashboardDto> recuperarDashboard(Pageable paginacao) {

        List<DashboardDto> marcas = marcaRepository.findAll(paginacao).getContent().stream().map(this::pegarInformacoesDaMarca).collect(Collectors.toList());

        return new PageImpl<>(marcas);
    }
}
