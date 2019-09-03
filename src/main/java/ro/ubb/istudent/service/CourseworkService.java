package ro.ubb.istudent.service;

import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.CourseworkDto;

import java.util.List;

/**
 * Created on 26.01.2018.
 */
public interface CourseworkService {
    List<CourseworkDto> getCompletedAssignmentsForCourse(String username, CourseDto course);
    List<CourseworkDto> getLeftAssignmentsForCourse(String username, CourseDto course);

    List<CourseworkDto> getCompletedExamsForCourse(String username, CourseDto course);
    List<CourseworkDto> getLeftExamsForCourse(String username, CourseDto course);

    int getOverallProgress(String username, CourseDto courseDto);
}
