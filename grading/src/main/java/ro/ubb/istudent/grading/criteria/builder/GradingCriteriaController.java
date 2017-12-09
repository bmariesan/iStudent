package ro.ubb.istudent.grading.criteria.builder;

import org.springframework.web.bind.annotation.*;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@RequestMapping("/criteria")
public class GradingCriteriaController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "Hello " + name;
    }
}
