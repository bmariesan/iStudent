package ro.ubb.istudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.designpatterns.strategy.*;
import ro.ubb.istudent.dto.CourseDTO;

import java.util.List;
import java.util.Queue;

@RestController
public class StatisticsAssignmentController {

    private Context context;

    @Autowired
    @Qualifier("assignmentsFeedbackTeachersStatistics")
    private AssignmentsFeedbackTeachersStatistics assignmentsFeedbackTeachersStatistics;

    @Autowired
    @Qualifier("assignmentsFeedbackStudentsStatistics")
    private AssignmentsFeedbackStudentsStatistics assignmentsFeedbackStudentsStatistics;

    @Autowired
    @Qualifier("completedAssignmentsCourseStatistics")
    private CompletedAssignmentsCourseStatistics completedAssignmentsCourseStatistics;

    @Autowired
    @Qualifier("assignmentsFilesStatistics")
    private AssignmentsFilesStatistics assignmentsFilesStatistics;

    @Autowired
    @Qualifier("allAttachementsStatistics")
    private AllAttachementsStatistics allAttachementsStatistics;

    @Autowired
    @Qualifier("maxNrOfAttachementsPerAssignmentStatistics")
    private MaxNrOfAttachementsPerAssignmentStatistics maxNrOfAttachementsPerAssignmentStatistics;
    

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public long getStatisticsFeedbackFromTeachers() {
        context = new Context(assignmentsFeedbackTeachersStatistics);
        return context.executeStrategy();
    }

    @RequestMapping(value = "/statistics/assignment/completed", method = RequestMethod.GET)
    public List<CourseDTO> getStatisticsForNumberOfCompletedAssignments() {
        return completedAssignmentsCourseStatistics.computeStatistics();
    }

    @RequestMapping(value = "/statistics/assignment/attachements", method = RequestMethod.GET)
    public long getNumberOfAssignmentsWithAttachements() {
        context = new Context(assignmentsFilesStatistics);
        return context.executeStrategy();
    }

    @RequestMapping(value = "/statistics/assignment/attachements/all", method = RequestMethod.GET)
    public long getAllAttachmentsNr() {
        context = new Context(allAttachementsStatistics);
        return context.executeStrategy();
    }

    @RequestMapping(value = "/statistics/assignment/attachements/max", method = RequestMethod.GET)
    public long getMaxNrOfAttachements() {
        context = new Context(maxNrOfAttachementsPerAssignmentStatistics);
        return context.executeStrategy();
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public long getNrAssignWithFeedbackFromStudent(){
        context = new Context(assignmentsFeedbackStudentsStatistics);
        return context.executeStrategy();
    }

}
