package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.GenderBasedStatisticsDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.enums.GenderEnum;
import ro.ubb.istudent.file_utils.FileFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class GenderBasedStatisticsService {

    private StudentService studentService;

    /**
     * @return List of students which passed all their tests.
     */
    public Optional<GenderBasedStatisticsDto> findGraduatedStudentsByGender(GenderEnum gender) {
        return studentService.findByGender(gender)
                .flatMap(studentDtos -> buildGenderBasedStatisticDto(studentDtos, gender));
    }

    private Optional<GenderBasedStatisticsDto> buildGenderBasedStatisticDto(List<StudentDto> studentList, GenderEnum gender) {
        GenderBasedStatisticsDto genderBasedStatisticsDto = new GenderBasedStatisticsDto();

        genderBasedStatisticsDto.setGender(gender);

        List<StudentDto> graduatedStudents = studentList.stream()
                .filter(studentDto -> checkIfAllTestsArePassed(studentDto.getTests()))
                .collect(Collectors.toList());

        if (graduatedStudents.size() == 0) {
            return Optional.empty();
        }
        else{
            FileFactory.makeAndPersist(graduatedStudents,gender.toString() + " graduates.");
        }

        genderBasedStatisticsDto.setGraduatedStudents(graduatedStudents);

        return Optional.of(genderBasedStatisticsDto);
    }

    private boolean checkIfAllTestsArePassed(List<TestDto> tests) {
        return tests.stream()
                .anyMatch(testDto -> testDto.getGrade() < testDto.getCourseDto().getMinimumGrade());
    }
}
