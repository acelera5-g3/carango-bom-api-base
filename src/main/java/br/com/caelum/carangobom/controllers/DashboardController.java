package br.com.caelum.carangobom.controllers;

import br.com.caelum.carangobom.dtos.DashboardDto;
import br.com.caelum.carangobom.services.DashboardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Cacheable(value = "dashboard")
    @ApiOperation("Recupera a dashboard")
    public ResponseEntity<Page<DashboardDto>> dashboard(
            Pageable paginacao
    ) {
        return ResponseEntity.ok(service.recuperarDashboard(paginacao));
    }

}
