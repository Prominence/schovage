package dev.zinchenko.schovage.app.stats.controller;

import dev.zinchenko.schovage.app.stats.dto.GeneralStatisticsResponse;
import dev.zinchenko.schovage.app.stats.service.StatisticsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistics")
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("general")
    public GeneralStatisticsResponse getGeneralStatistics() {
        return statisticsService.getGeneralStatistics();
    }
}
