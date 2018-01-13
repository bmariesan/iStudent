package ro.ubb.istudent.grading.gradingbook;


import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Optional;


@RestController
@RequestMapping("/gradingbook")
public class GradingBookController {


    private final GradingBookService service;
    public GradingBookController(GradingBookService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity insert(
            @RequestBody GradingBook gradingBook) {
        return new ResponseEntity<>(service.save(gradingBook), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity studentGetByCourseId(
            @PathVariable ObjectId id) {
        return new ResponseEntity<>(service.getByID(id, true), HttpStatus.ACCEPTED)
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity teacherGetByCourseId(
            @pathVariable ObjectId id) {
        return new ResponseEntity<>(service.getByID(id, false), HttpStatus.ACCEPTED)
    }

}