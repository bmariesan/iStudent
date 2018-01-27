package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CourseBasedStatisticsService { //+valer

    private StudentService studentService;

    public Optional<CourseBasedStatisticsDto> findGraduatedStudentsByCourse(CourseDto courseDto){
        return buildCourseBasedStatisticsDto(courseDto);
    }

    private Optional<CourseBasedStatisticsDto> buildCourseBasedStatisticsDto(CourseDto courseDto){
        CourseBasedStatisticsDto courseBasedStatisticsDto = new CourseBasedStatisticsDto();

        List<StudentDto> studentList=studentService.findAll();

        courseBasedStatisticsDto.setCourseName(courseDto.getCourseName());

        List<StudentDto> enrolledStudents = studentList.stream()
                .filter(studentDto -> TestService.checkIfCourseExist(studentDto.getTests(),courseDto.getCourseName()))
                .collect(Collectors.toList());

        courseBasedStatisticsDto.setTotalNumberOfStudents(enrolledStudents.size());

        List<StudentDto> graduatedStudents = enrolledStudents.stream()
                .filter(studentDto -> TestService.checkIfTestFromCourseIsPassed(studentDto.getTests(),courseDto.getCourseName()))
                .collect(Collectors.toList());

        courseBasedStatisticsDto.setGraduatedStudentsNumber(graduatedStudents.size());

        if (graduatedStudents.size() == 0) {
            return Optional.empty();
        }

        courseBasedStatisticsDto.setGraduatedStudents(graduatedStudents);

        return Optional.of(courseBasedStatisticsDto);
    }

    /*private boolean checkIfTestFromCourseIsPassed(List<TestDto> tests,String coursename) { //Moved in TestService to avoid code duplication
        return tests.stream()
                .filter(testDto ->  testDto.getCourseDto().getCourseName().equals(coursename))
                .anyMatch(testDto -> testDto.getGrade() >= testDto.getCourseDto().getMinimumGrade());
    } commented by Valer*/

    /*private boolean checkIfCourseExist(List<TestDto> tests, String coursename){  //Moved in TestService to avoid code duplication
        return tests.stream()
                .anyMatch(testDto -> testDto.getCourseDto().getCourseName().equals(coursename));
    } commented by Valer*/
}
