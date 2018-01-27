package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.TestConverter;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.repository.TestRepository;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class TestService {

    private TestRepository repository;

    private TestConverter testConverter;

    public TestDto save(TestDto studentDto) {
        return testConverter.createFromEntity(repository.save(testConverter.createFromDto(studentDto)));
    }

    public static boolean checkIfAllTestsArePassed(List<TestDto> tests) { //+Valer
        return tests.stream()
                //.anyMatch(testDto -> testDto.getGrade() < testDto.getCourseDto().getMinimumGrade()); Commented by Valer
                .allMatch(testDto -> testDto.getGrade() >= testDto.getCourseDto().getMinimumGrade());
    }

    public static boolean checkIfTestFromCourseIsPassed(List<TestDto> tests,String coursename) { //+Valer
        return tests.stream()
                .filter(testDto ->  testDto.getCourseDto().getCourseName().equals(coursename))
                .anyMatch(testDto -> testDto.getGrade() >= testDto.getCourseDto().getMinimumGrade());
    }

    public static boolean checkIfCourseExist(List<TestDto> tests, String coursename){ //+Valer
        return tests.stream()
                .anyMatch(testDto -> testDto.getCourseDto().getCourseName().equals(coursename));
    }
}
