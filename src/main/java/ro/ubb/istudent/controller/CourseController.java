package ro.ubb.istudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String course() {
        return "courses";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String history() {
        return "history";
    }

}