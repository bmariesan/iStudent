package ro.ubb.istudent.grading.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.exam.domain.CompletedExam;
import ro.ubb.istudent.grading.exam.domain.CompletedExercise;
import ro.ubb.istudent.grading.exam.domain.Exam;
import ro.ubb.istudent.grading.exam.domain.Exercise;

import java.util.List;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/exams")
public class GradingExamController {

    @ResponseBody
    @GetMapping("/score")
    public ResponseEntity<Double> gradeMultipleChoiceExam(
            @RequestBody CompletedExam exam) {
        return new ResponseEntity<>(exam.totalScore(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/exercises/correct")
    public ResponseEntity<List<Exercise>> getUsersCorrectExercises(
            @RequestBody CompletedExam exam) {
        return new ResponseEntity<>(exam.correctExercises(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/exercises/partially")
    public ResponseEntity<List<Exercise>> getUsersPartiallyCorrectExercises(
            @RequestBody CompletedExam exam) {
        return new ResponseEntity<>(exam.partiallyCorrectExercises(), HttpStatus.OK);
    }
}
