package ro.ubb.istudent.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.dto.GenderBasedStatisticsDto;
import ro.ubb.istudent.enums.Gender;
import ro.ubb.istudent.service.GenderBasedStatisticsService;
import ro.ubb.istudent.service.GreetingService;
import ro.ubb.istudent.util.ResponseUtil;

@RequestMapping("/api")
@RestController
@Slf4j
public class GenderBasedStatistics {

    private static final String COUNTRY_BASED_STATISTICS_URL = "/statistics/country";

    private final String baseUrl;

    private GenderBasedStatisticsService genderBasedStatisticsService;


    public GenderBasedStatistics(GenderBasedStatisticsService genderBasedStatisticsService, @Value("${application.base-url}") String baseUrl) {
        this.genderBasedStatisticsService = genderBasedStatisticsService;
        this.baseUrl = baseUrl;
    }

    @GetMapping(COUNTRY_BASED_STATISTICS_URL + "/{gender}")
    public ResponseEntity getGraduatedStudentsByGender(@PathVariable("gender") String gender) {
        return ResponseUtil.wrapOrNotFound(genderBasedStatisticsService.findGraduatedStudentsByGender(Gender.valueOf(gender)));
    }
}
