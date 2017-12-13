package ro.ubb.istudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.ubb.istudent.service.AssignmentService;

@Controller
public class StatisticsAssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String getStatisticsFeedbackFromTeachers() {
        return String.valueOf(assignmentService.getNumberOfAssignmentsHavingFeedbackFromTeachers());
    }

}
