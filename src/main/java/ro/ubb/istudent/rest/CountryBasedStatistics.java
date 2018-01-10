package ro.ubb.istudent.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.service.CountryBasedStatisticsService;
import ro.ubb.istudent.service.CountryService;
import ro.ubb.istudent.util.ResponseUtil;

import static ro.ubb.istudent.util.ResponseUtil.STATISTICS_URL;

@RequestMapping("/api")
@RestController
@Slf4j
public class CountryBasedStatistics {
    private final String baseUrl;

    private CountryBasedStatisticsService countryBasedStatisticsService;

    private CountryService countryService;

    public CountryBasedStatistics(@Value("${application.base-url}") String baseUrl, CountryBasedStatisticsService countryBasedStatisticsService, CountryService countryService) {
        this.baseUrl = baseUrl;
        this.countryBasedStatisticsService = countryBasedStatisticsService;
        this.countryService = countryService;
    }

    @GetMapping(STATISTICS_URL + "/country/{country}")
    public ResponseEntity getGraduatedStudentsByCountry(@PathVariable("country") String country) {
        return ResponseUtil.wrapOrNotFound(countryBasedStatisticsService.findGraduatedStudentsByCountry(countryService.findByCountryName(country).get()));
    }
}
