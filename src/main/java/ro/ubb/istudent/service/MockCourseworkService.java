package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.CourseworkDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 26.01.2018.
 */
@Service
public class MockCourseworkService implements CourseworkService {
    private static final int NUM_ASSIGNMENTS = 12;
    private static final int NUM_EXAMS = 4;
    private static final int NUM_ASSIGNMENTS_COMPLETED = 5;
    private static final int NUM_EXAMS_COMPLETED = 1;

    @Override
    public List<CourseworkDto> getCompletedAssignmentsForCourse(String username, CourseDto course) {
        List<CourseworkDto> assignments = new ArrayList<>();
        for (int i = 1; i <= NUM_ASSIGNMENTS_COMPLETED; i++) {
            String name = "Assignment #" + i;
            int grade = getRandomGrade();
            assignments.add(new CourseworkDto(name, CourseworkDto.Type.Assignment, true, grade));
        }
        return assignments;
    }

    @Override
    public List<CourseworkDto> getLeftAssignmentsForCourse(String username, CourseDto course) {
        List<CourseworkDto> assignments = new ArrayList<>();
        for (int i = NUM_ASSIGNMENTS_COMPLETED + 1; i <= NUM_ASSIGNMENTS; i++) {
            String name = "Assignment #" + i;
            assignments.add(new CourseworkDto(name, CourseworkDto.Type.Assignment, false, -1));
        }
        return assignments;
     }

    @Override
    public List<CourseworkDto> getCompletedExamsForCourse(String username, CourseDto course) {
        List<CourseworkDto> exams = new ArrayList<>();
        for (int i = 1; i <= NUM_EXAMS_COMPLETED; i++) {
            String name = "Exam #" + i;
            int grade = getRandomGrade();
            exams.add(new CourseworkDto(name, CourseworkDto.Type.Exam, true, grade));
        }
        return exams;
    }

    @Override
    public List<CourseworkDto> getLeftExamsForCourse(String username, CourseDto course) {
        List<CourseworkDto> exams = new ArrayList<>();
        for (int i = NUM_EXAMS_COMPLETED + 1; i <= NUM_EXAMS; i++) {
            String name = "Exam #" + i;
            exams.add(new CourseworkDto(name, CourseworkDto.Type.Exam, false, -1));
        }
        return exams;
    }

    @Override
    public int getOverallProgress(String username, CourseDto courseDto) {
        int totalCoursework = NUM_ASSIGNMENTS + NUM_EXAMS;
        int completedCoursework = NUM_ASSIGNMENTS_COMPLETED + NUM_EXAMS_COMPLETED;
        return completedCoursework * 100 / totalCoursework;
    }

    private int getRandomGrade() {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }
}
