package ro.ubb.istudent.grading.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.exam.CompletedUnitOfWork;
import ro.ubb.istudent.grading.service.GradingWorkFlowService;
import ro.ubb.istudent.grading.gradingbook.Grade;

import java.util.List;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/work-flow")
public class GradingWorkFlowController {

    private final GradingWorkFlowService service;

    @Autowired
    public GradingWorkFlowController(GradingWorkFlowService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/grade")
    public ResponseEntity<Grade> gradeUnitOfWork(
            @RequestBody CompletedUnitOfWork unitOfWork) {
        return new ResponseEntity<>(service.gradeUnitOfWork(unitOfWork),
                HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Course> addUnitOfWorkForStudent(
            @RequestParam String courseId,
            @RequestBody CompletedUnitOfWork unitOfWork) {
        return new ResponseEntity<>(service.addUnitOfWorkFromStudent(
                unitOfWork, new ObjectId(courseId)), HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/all")
    public ResponseEntity<List<Grade>> gradeCourseForAllStudents(
            @RequestParam String courseId) {
        // TODO: Save result in database.
        return new ResponseEntity<>(service.gradeCourseForEachStudent(
                new ObjectId(courseId)), HttpStatus.OK);
    }
}
