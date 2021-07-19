package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.services.DashboardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/dashboard")
public class DashboardController {

    private DashboardService service;

    @Autowired
    public DashboardController(
            DashboardService service
    ) {
        this.service = service;
    }

    @GetMapping()
    // TODO: Reativar o cache para essa chamada (deve considerar dar purge nos posts/del de veículos e marcas)
    // @Cacheable(value = "dashboard")
    @ApiOperation("Recupera a dashboard")
    public ResponseEntity<List<DashboardDto>> dashboard() {
        return ResponseEntity.ok(service.recuperarDashboard());
    }

}
