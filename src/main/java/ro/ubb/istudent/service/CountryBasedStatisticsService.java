package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.CountryBasedStatisticsDto;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CountryBasedStatisticsService {

    private StudentService studentService;

    public Optional<CountryBasedStatisticsDto> findGraduatedStudentsByCountry(CountryDto countryDto) {
        return studentService.findByCountry(countryDto)
                .flatMap(studentDtos -> buildCountryBasedStatisticsDto(studentDtos, countryDto));
    }

    private Optional<CountryBasedStatisticsDto> buildCountryBasedStatisticsDto(List<StudentDto> studentList, CountryDto countryDto) {
        CountryBasedStatisticsDto countryBasedStatisticsDto = new CountryBasedStatisticsDto();

        countryBasedStatisticsDto.setCountry(countryDto);

        List<StudentDto> graduatedStudents = studentList.stream()
                .filter(studentDto -> checkIfAllTestsArePassed(studentDto.getTests()))
                .collect(Collectors.toList());

        if (graduatedStudents.size() == 0) {
            return Optional.empty();
        }

        countryBasedStatisticsDto.setGraduatedStudents(graduatedStudents);

        return Optional.of(countryBasedStatisticsDto);
    }

    private boolean checkIfAllTestsArePassed(List<TestDto> tests) {
        return tests.stream()
                .anyMatch(testDto -> testDto.getGrade() < testDto.getCourseDto().getMinimumGrade());
    }
}
