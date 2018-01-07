package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.grading.domain.GradingBook;

/**
 * Created by Marius on 10.12.2017.
 */
@RestController
@RequestMapping("/gradingbook")
public class GradingBookController {

    @Autowired
    GradingBookRepository repo;

    @GetMapping("/{id}")
    public String hello(@PathVariable("id") String id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(repo.findOne(id));
    }


}