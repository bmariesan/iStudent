package ro.ubb.istudent.grading.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.GradingBook;
import ro.ubb.istudent.grading.gradingbook.User;
import ro.ubb.istudent.grading.service.GradingBookService;

/**
 * Created by Marius on 10.12.2017.
 */
@RestController
@RequestMapping("/grading-book")
public class GradingBookController {

    private final GradingBookService service;

    @Autowired
    public GradingBookController(GradingBookService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Course> addGradingBookToCourse(
            @RequestParam String courseId,
            @RequestBody GradingBook gradingBook) {
        return new ResponseEntity<>(service.saveGradingBookToCourse(
                new ObjectId(courseId), gradingBook), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<GradingBook> getGradingBookFromCourse(
            @RequestParam String courseId,
            @RequestBody User user) {
        return new ResponseEntity<>(service.getGradingBookFromCourse(
                new ObjectId(courseId), user), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/")
    public ResponseEntity<Course> deleteGradingBookFromCourse(
            @RequestParam String courseId) {
        return new ResponseEntity<>(service.deleteGradingBookFromCourse(
                new ObjectId(courseId)), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/grade")
    public ResponseEntity<Course> addGradeToGradingBook(
            @RequestParam String courseId,
            @RequestBody Grade grade) {
        return new ResponseEntity<>(service.storeGradeInGradingBook(
                grade, new ObjectId(courseId)), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/grade")
    public ResponseEntity<Course> deleteGradeFromGradingBook(
            @RequestParam String courseId,
            @RequestParam String gradeId) {
        return new ResponseEntity<>(service.deleteGradeFromGradingBook(
                new ObjectId(gradeId), new ObjectId(courseId)), HttpStatus.OK);
    }
}
