package exams.rest;

import exams.domain.Exam;
import exams.service.Service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api")
@RestController
public class StatisticResource {
    private static final String GREETING_CONTROLLER_MAPPING = "/examStatistic";
    private final Service service;
    private final String baseUrl;

    public StatisticResource(Service service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @GetMapping("/statistics")
    public String getHelloWorldGreeting() {
        System.out.println("what");
        //System.out.println(service.findAllGreetings());
        //return ResponseUtil.wrapOrNotFound(service.findGreetingById(greetingId));
        return "what";
    }

    //todo take the exams(not just titles) and parse it on frontend - I need the ID!
    @GetMapping("/exams")
    public List<String> getExams(){
        List<String> examList = new ArrayList<>();
        service.getExams().forEach(e -> examList.add(e.getTitle()));
        return examList;
    }

    @GetMapping("/examsAsEntity")
    public List<Exam> getExamsAsEntity(){
        return service.getExams();
    }
}
