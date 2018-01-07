package ro.ubb.istudent.grading.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseNotFound;
import ro.ubb.istudent.grading.domain.ChoiceQuestion;
import ro.ubb.istudent.grading.service.ChoiceQuestionService;

/**
 * @author Alexandru Stoica
 */

@RestController
@RequestMapping("/choice-question")
public class ChoiceQuestionController {

    @Autowired
    private ChoiceQuestionService choiceQuestionService;

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Course> insert(
            @RequestBody ChoiceQuestion choiceQuestion) {
        return new ResponseEntity<>(
                choiceQuestionService.insert(choiceQuestion),
                HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @PutMapping("")
    public ResponseEntity<Course> update(
            @RequestBody ChoiceQuestion choiceQuestion) {
        return new ResponseEntity<>(
                choiceQuestionService.update(choiceQuestion),
                HttpStatus.ACCEPTED);
    }
    @ResponseBody
    @DeleteMapping("")
    public ResponseEntity<Course> delete(@RequestParam String courseId) {
        return new ResponseEntity<>(
                choiceQuestionService.delete(new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @GetMapping("/{courseId}")
    public ResponseEntity<ChoiceQuestion> getByCourseId(
            @PathVariable String courseId) {
        return new ResponseEntity<>(
                choiceQuestionService.getByCourseId(new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @PostMapping("/redistribution")
    public ResponseEntity<Course> insertWithComponentsRedistribution(
            @RequestParam String courseId,
            @RequestBody ChoiceQuestion choiceQuestion) {
        return new ResponseEntity<>(
                choiceQuestionService.insertWithComponentsRedistribution(
                        choiceQuestion, new ObjectId(courseId)),
                HttpStatus.ACCEPTED);
    }

    @ExceptionHandler({CourseNotFound.class, ChoiceQuestionNotFound.class})
    public ResponseEntity exceptionHandler() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
