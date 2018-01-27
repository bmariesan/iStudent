package ro.ubb.istudent.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.dto.AgeDto;
import ro.ubb.istudent.service.StudentService;
import ro.ubb.istudent.util.ResponseUtil;

import static ro.ubb.istudent.util.ResponseUtil.STATISTICS_URL;

@RequestMapping("/api")
@RestController
@Slf4j
public class AgeBasedStatistics {
    private final String baseUrl;

    private StudentService studentService;

    public AgeBasedStatistics(@Value("${application.base-url}") String baseUrl, StudentService studentService) {
        this.baseUrl = baseUrl;
        this.studentService = studentService;
    }

    @PostMapping(value = STATISTICS_URL + "/age", consumes = "application/json", produces = "application/json")
    public ResponseEntity getStudentsBetweenAge(@RequestBody AgeDto ageDto) {
        if (null == ageDto.getMaxAge()) {
            return ResponseUtil.wrapOrNotFound(studentService.findByAgeGreaterThanOrEqual(ageDto.getMinAge()));
        }
        return ResponseUtil.wrapOrNotFound(studentService.findByAgeBetween(ageDto));
    }
}
