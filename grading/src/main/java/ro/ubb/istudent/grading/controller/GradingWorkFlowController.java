package ro.ubb.istudent.grading.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.exam.CompletedUnitOfWork;
import ro.ubb.istudent.grading.exam.GradingFormulaType;
import ro.ubb.istudent.grading.service.GradingBookService;
import ro.ubb.istudent.grading.service.GradingWorkFlowService;
import ro.ubb.istudent.grading.gradingbook.Grade;

import java.awt.*;
import java.util.List;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/work-flow")
public class GradingWorkFlowController {

    private final GradingWorkFlowService service;
    private final GradingBookService gradingBookService;

    @Autowired
    public GradingWorkFlowController(
            final GradingWorkFlowService gradingWorkFlowService,
            final GradingBookService gradingBookService) {
        this.service = gradingWorkFlowService;
        this.gradingBookService = gradingBookService;
    }

    @ResponseBody
    @GetMapping("/grade")
    public ResponseEntity<Grade> gradeUnitOfWork(
            @RequestBody CompletedUnitOfWork unitOfWork) {
        return new ResponseEntity<>(service.gradeUnitOfWork(unitOfWork),
                HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("")
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
        ObjectId id = new ObjectId(courseId);
        List<Grade> grades = service.gradeCourseForEachStudent(id);
        grades.forEach(it -> gradingBookService.storeGradeInGradingBook(it, id));
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/all/type")
    public ResponseEntity<List<Grade>> gradeCourseForAllStudents(
            @RequestParam GradingFormulaType type,
            @RequestParam String courseId) {
        ObjectId id = new ObjectId(courseId);
        List<Grade> grades = service.gradeCourseForEachStudentBasedOnFormula(id, type);
        grades.forEach(it -> gradingBookService.storeGradeInGradingBook(it, id));
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }
}
