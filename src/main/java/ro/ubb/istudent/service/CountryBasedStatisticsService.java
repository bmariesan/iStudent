package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.CountryBasedStatisticsDto;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.dto.*;

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
                .flatMap(studentDto -> buildCountryBasedStatisticsDto(studentDto, countryDto));
    }

    private Optional<CountryBasedStatisticsDto> buildCountryBasedStatisticsDto(List<StudentDto> studentList, CountryDto countryDto) {
        CountryBasedStatisticsDto countryBasedStatisticsDto = new CountryBasedStatisticsDto();

        countryBasedStatisticsDto.setCountry(countryDto);

        List<StudentDto> graduatedStudents = studentList.stream()
                .filter(studentDto -> TestService.checkIfAllTestsArePassed(studentDto.getTests()))
                .collect(Collectors.toList());

        if (graduatedStudents.size() == 0) {
            return Optional.empty();
        }

        countryBasedStatisticsDto.setGraduatedStudents(graduatedStudents);

        return Optional.of(countryBasedStatisticsDto);
    }
}
