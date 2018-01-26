package ro.ubb.istudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    public String allCourses() { return "allCourses"; }

    @RequestMapping(value = "/availableCourses", method = RequestMethod.GET)
    public String availableCourses() {
        return "availableCourses";
    }

    @RequestMapping(value = "/historyCourses", method = RequestMethod.GET)
    public String historyCourses() {
        return "historyCourses";
    }

    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    public String progress() {
        return "progress";
    }
}