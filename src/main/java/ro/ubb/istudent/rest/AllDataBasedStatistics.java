package ro.ubb.istudent.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.service.AllDataBasedStatisticsService;
import ro.ubb.istudent.util.ResponseUtil;


import static ro.ubb.istudent.util.ResponseUtil.STATISTICS_URL;

@RequestMapping("/api")
@RestController
@Slf4j
public class AllDataBasedStatistics { //+Valer

    private final String baseUrl;

    private AllDataBasedStatisticsService allDataBasedStatisticsService;

    public AllDataBasedStatistics(@Value("${application.base-url}") String baseUrl, AllDataBasedStatisticsService allDataBasedStatisticsService) {
        this.allDataBasedStatisticsService = allDataBasedStatisticsService;
        this.baseUrl = baseUrl;
    }

    @GetMapping(STATISTICS_URL + "/alldata")
    public ResponseEntity getAllData() {
        return ResponseUtil.wrapOrNotFound(allDataBasedStatisticsService.findAllStudents());
    }
}
