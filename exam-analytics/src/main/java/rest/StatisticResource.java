package rest;

import org.springframework.beans.factory.annotation.Value;
import service.StatisticService;

public class StatisticResource {
    private static final String GREETING_CONTROLLER_MAPPING = "/examStatistic";
    private final StatisticService service;
    private final String baseUrl;

    public StatisticResource(StatisticService service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }
}
