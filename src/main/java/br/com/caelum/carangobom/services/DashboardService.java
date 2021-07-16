package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.DashboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class DashboardService {

    private VeiculoService veiculoService;


    @Autowired
    DashboardService(
            VeiculoService veiculoService
    ) {
        this.veiculoService = veiculoService;
    }

    public List<DashboardDto> recuperarDashboard() {

        DashboardDto dashboard = new DashboardDto();

        final Long[] somatoria = {0L};
        veiculoService.buscarTodos(Pageable.unpaged()).get().forEach( veiculo -> {
            somatoria[0] += veiculo.getValor();
        } );

        dashboard.setSomatoria(somatoria[0]);
        return List.of(dashboard);
    }
}
