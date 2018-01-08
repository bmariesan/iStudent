package ro.ubb.istudent.grading.exam;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexandru Stoica
 */

@Immutable
@RestController
@RequestMapping("/grading-tests")
public class GradingExamController {

    @Autowired
    private final GradingExamService service;

    public GradingExamController(
            final GradingExamService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Double> gradeMultipleChoiceExam(@RequestBody Exam exam) {
        return new ResponseEntity<>(service.getTotalScoreFromExam(exam),
                HttpStatus.OK);
    }
}
