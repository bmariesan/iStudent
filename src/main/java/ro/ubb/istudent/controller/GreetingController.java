package ro.ubb.istudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.service.CourseService;
import ro.ubb.istudent.service.StudentService;
import ro.ubb.istudent.service.TestService;

import java.util.Arrays;

@Controller
public class GreetingController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

}