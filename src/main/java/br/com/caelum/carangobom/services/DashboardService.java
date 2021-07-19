package br.com.caelum.carangobom.services;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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

    public List<DashboardDto> recuperarDashboard() {

        DashboardDto dashboard = new DashboardDto();

        // TODO: implementar restante
        marcaRepository.findAll();
        /* marcaRepository.findAll().forEach(marca -> marca.getVeiculos().forEach(veiculo -> {
            System.out.println(veiculo);
        })); */
        return List.of(dashboard);
    }
}
