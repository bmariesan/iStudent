package ro.ubb.istudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.service.AssignmentService;

@RestController
public class StatisticsAssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public long getStatisticsFeedbackFromTeachers() {
            return assignmentService.getNumberOfAssignmentsHavingFeedbackFromTeachers();
    }

}
