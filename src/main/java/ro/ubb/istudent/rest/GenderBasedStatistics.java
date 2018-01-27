package ro.ubb.istudent.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.enums.GenderEnum;
import ro.ubb.istudent.service.GenderBasedStatisticsService;
import ro.ubb.istudent.util.ResponseUtil;

import static ro.ubb.istudent.util.ResponseUtil.STATISTICS_URL;

@RequestMapping("/api")
@RestController
@Slf4j
public class GenderBasedStatistics {

    private final String baseUrl;

    private GenderBasedStatisticsService genderBasedStatisticsService;

    public GenderBasedStatistics(@Value("${application.base-url}") String baseUrl, GenderBasedStatisticsService genderBasedStatisticsService) {
        this.genderBasedStatisticsService = genderBasedStatisticsService;
        this.baseUrl = baseUrl;
    }

    @GetMapping(STATISTICS_URL + "/gender/{gender}")
    public ResponseEntity getGraduatedStudentsByGender(@PathVariable("gender") String gender) {
        return ResponseUtil.wrapOrNotFound(genderBasedStatisticsService.findGraduatedStudentsByGender(EnumUtils.getEnum(GenderEnum.class, gender.toLowerCase())));
    }
}