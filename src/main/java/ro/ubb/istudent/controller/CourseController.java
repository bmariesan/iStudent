package ro.ubb.istudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {

    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    public String allCourses() { return "allCourses"; }

    @RequestMapping(value = "/availableCourses", method = RequestMethod.GET)
    public String availableCourses() {
        return "availableCourses";
    }

}