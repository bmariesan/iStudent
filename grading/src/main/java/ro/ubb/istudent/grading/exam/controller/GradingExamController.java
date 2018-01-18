package ro.ubb.istudent.grading.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.exam.domain.CompletedExam;
import ro.ubb.istudent.grading.exam.domain.ExamWithCompletedExercises;
import ro.ubb.istudent.grading.exam.domain.Exercise;
import ro.ubb.istudent.grading.exam.service.GradingExamService;
import ro.ubb.istudent.grading.gradingbook.domain.Grade;

import java.util.List;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/exams")
public class GradingExamController {

    private final GradingExamService service;

    @Autowired
    public GradingExamController(GradingExamService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/score")
    public ResponseEntity<Double> gradeMultipleChoiceExam(
            @RequestBody ExamWithCompletedExercises exam) {
        return new ResponseEntity<>(exam.totalScore(), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/grade")
    public ResponseEntity<Grade> gradeCompletedExam(
            @RequestBody ExamWithCompletedExercises exam) {
        return new ResponseEntity<>(service.grade(exam), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/exercises/correct")
    public ResponseEntity<List<Exercise>> getUsersCorrectExercises(
            @RequestBody ExamWithCompletedExercises exam) {
        return new ResponseEntity<>(exam.correctExercises(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/exercises/partially")
    public ResponseEntity<List<Exercise>> getUsersPartiallyCorrectExercises(
            @RequestBody ExamWithCompletedExercises exam) {
        return new ResponseEntity<>(exam.partiallyCorrectExercises(), HttpStatus.OK);
    }
}
