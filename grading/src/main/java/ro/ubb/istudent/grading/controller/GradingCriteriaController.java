package ro.ubb.istudent.grading.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.exception.CourseNotFound;
import ro.ubb.istudent.grading.exception.GradingCriteriaNotFound;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.service.GradingCriteriaService;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/grading-criteria")
public class GradingCriteriaController {

    private final GradingCriteriaService gradingCriteriaService;

    @Autowired
    public GradingCriteriaController(
            final GradingCriteriaService gradingCriteriaService) {
        this.gradingCriteriaService = gradingCriteriaService;
    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Course> insert(
            @RequestParam String courseId,
            @RequestBody GradingCriteria gradingCriteria) {
        return new ResponseEntity<>(gradingCriteriaService.saveGradingCriteriaToCourse(
                gradingCriteria, new ObjectId(courseId)), HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @PutMapping("")
    public ResponseEntity<Course> update(
            @RequestParam String courseId,
            @RequestBody GradingCriteria gradingCriteria) {
        return new ResponseEntity<>(gradingCriteriaService.saveGradingCriteriaToCourse(
                gradingCriteria, new ObjectId(courseId)), HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @DeleteMapping("")
    public ResponseEntity<Course> delete(@RequestParam String courseId) {
        return new ResponseEntity<>(gradingCriteriaService.deleteGradingCriteriaFromCourse(
                new ObjectId(courseId)), HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @PostMapping("/redistribution")
    public ResponseEntity<Course> insertWithComponentsRedistribution(
            @RequestParam String courseId,
            @RequestBody GradingCriteria gradingCriteria) {
        return new ResponseEntity<>(gradingCriteriaService.saveGradingCriteriaWithRedistribution(
                gradingCriteria, new ObjectId(courseId)), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler({CourseNotFound.class, GradingCriteriaNotFound.class})
    public ResponseEntity exceptionHandler() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
