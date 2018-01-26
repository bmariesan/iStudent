package ro.ubb.istudent.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.service.*;
import ro.ubb.istudent.util.ResponseUtil;



import static ro.ubb.istudent.util.ResponseUtil.STATISTICS_URL;

@RequestMapping("/api")
@RestController
@Slf4j
public class CourseBasedStatistics { //+Valer
    private final String baseUrl;

    private CourseBasedStatisticsService courseBasedStatisticsService;

    private CourseService courseService;

    public CourseBasedStatistics(@Value("${application.base-url}") String baseUrl, CourseBasedStatisticsService courseBasedStatisticsService, CourseService courseService){//,CourseConverter courseConverter,TestConverter testConverter) {
        this.baseUrl = baseUrl;
        this.courseBasedStatisticsService =  courseBasedStatisticsService;
        this.courseService = courseService;
    }

    @GetMapping(STATISTICS_URL + "/course/{course}")
    public ResponseEntity getGraduatedStudentsByCountry(@PathVariable("course") String course) {
        return ResponseUtil.wrapOrNotFound(courseBasedStatisticsService.findGraduatedStudentsByCourse(courseService.findByCourseName(course).get()));
    }
}

