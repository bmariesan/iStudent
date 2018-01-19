package ro.ubb.istudent.grading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.exam.UnitOfWorkWithCompletedExercises;
import ro.ubb.istudent.grading.exam.Exercise;
import ro.ubb.istudent.grading.service.GradingWorkFlowService;
import ro.ubb.istudent.grading.gradingbook.Grade;

import java.util.List;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/exams")
public class GradingExamController {

    private final GradingWorkFlowService service;

    @Autowired
    public GradingExamController(GradingWorkFlowService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/score")
    public ResponseEntity<Double> gradeMultipleChoiceExam(
            @RequestBody UnitOfWorkWithCompletedExercises exam) {
        return new ResponseEntity<>(exam.totalScore(), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/grade")
    public ResponseEntity<Grade> gradeCompletedExam(
            @RequestBody UnitOfWorkWithCompletedExercises exam) {
//        return new ResponseEntity<>(service.grade(exam), HttpStatus.OK);
        return null;
    }


    @ResponseBody
    @GetMapping("/exercises/correct")
    public ResponseEntity<List<Exercise>> getUsersCorrectExercises(
            @RequestBody UnitOfWorkWithCompletedExercises exam) {
        return new ResponseEntity<>(exam.correctExercises(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/exercises/partially")
    public ResponseEntity<List<Exercise>> getUsersPartiallyCorrectExercises(
            @RequestBody UnitOfWorkWithCompletedExercises exam) {
        return new ResponseEntity<>(exam.partiallyCorrectExercises(), HttpStatus.OK);
    }
}
