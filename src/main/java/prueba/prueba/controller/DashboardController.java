package prueba.prueba.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.prueba.application.DashboardService;
import prueba.prueba.dto.DashboardSummaryDTO;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardSummaryDTO getDashboard() {
        return dashboardService.getDashboardSummary();
    }
}