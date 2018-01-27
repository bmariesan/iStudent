package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.GenderBasedStatisticsDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.enums.GenderEnum;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class GenderBasedStatisticsService {

    private StudentService studentService;

    public Optional<GenderBasedStatisticsDto> findGraduatedStudentsByGender(GenderEnum gender) {
        return studentService.findByGender(gender)
                .flatMap(studentDto -> buildGenderBasedStatisticDto(studentDto, gender));
    }

    private Optional<GenderBasedStatisticsDto> buildGenderBasedStatisticDto(List<StudentDto> studentList, GenderEnum gender) {
        GenderBasedStatisticsDto genderBasedStatisticsDto = new GenderBasedStatisticsDto();

        genderBasedStatisticsDto.setGender(gender);

        List<StudentDto> graduatedStudents = studentList.stream()
                .filter(studentDto -> TestService.checkIfAllTestsArePassed((studentDto.getTests())))
                .collect(Collectors.toList());

        if (graduatedStudents.size() == 0) {
            return Optional.empty();
        }

        genderBasedStatisticsDto.setGraduatedStudents(graduatedStudents);

        return Optional.of(genderBasedStatisticsDto);
    }

    /*private boolean checkIfAllTestsArePassed(List<TestDto> tests) { //Moved in TestService to avoid code duplication
        return tests.stream()
                //.anyMatch(testDto -> testDto.getGrade() < testDto.getCourseDto().getMinimumGrade()); Commented by Valer
                .allMatch(testDto -> testDto.getGrade() >= testDto.getCourseDto().getMinimumGrade());
    } commented by Valer */
}
