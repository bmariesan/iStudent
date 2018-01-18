package ro.ubb.istudent.grading.criteria.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseNotFound;
import ro.ubb.istudent.grading.criteria.exception.GradingCriteriaNotFound;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteria;
import ro.ubb.istudent.grading.criteria.service.GradingCriteriaService;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/grading-criteria")
public class GradingCriteriaController {

    @Autowired
    private GradingCriteriaService gradingCriteriaService;

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Course> insert(
            @RequestParam String courseId,
            @RequestBody GradingCriteria gradingCriteria) {
        return new ResponseEntity<>(
                gradingCriteriaService.insert(gradingCriteria, new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @PutMapping("")
    public ResponseEntity<Course> update(
            @RequestParam String courseId,
            @RequestBody GradingCriteria gradingCriteria) {
        return new ResponseEntity<>(
                gradingCriteriaService.update(gradingCriteria, new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }
    @ResponseBody
    @DeleteMapping("")
    public ResponseEntity<Course> delete(@RequestParam String courseId) {
        return new ResponseEntity<>(
                gradingCriteriaService.delete(new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @GetMapping("/{courseId}")
    public ResponseEntity<GradingCriteria> getByCourseId(
            @PathVariable String courseId) {
        return new ResponseEntity<>(
                gradingCriteriaService.getByCourseId(new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @PostMapping("/redistribution")
    public ResponseEntity<Course> insertWithComponentsRedistribution(
            @RequestParam String courseId,
            @RequestBody GradingCriteria gradingCriteria) {
        return new ResponseEntity<>(
                gradingCriteriaService.insertWithComponentsRedistribution(
                        gradingCriteria, new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }

    @ExceptionHandler({CourseNotFound.class, GradingCriteriaNotFound.class})
    public ResponseEntity exceptionHandler() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
