package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.*;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AllDataBasedStatisticsService { //+Valer

    private StudentService studentService;

    public Optional<AllDataBasedStatisticsDto> findAllStudents() {

        return buildAllDataBasedStatisticsDto(studentService.findAll());
    }

    private Optional<AllDataBasedStatisticsDto> buildAllDataBasedStatisticsDto(List<StudentDto> enrolledStudents){
        AllDataBasedStatisticsDto allDataBasedStatisticsDto = new AllDataBasedStatisticsDto();

        allDataBasedStatisticsDto.setTotalNumberOfStudents(enrolledStudents.size());

        if (enrolledStudents.size() == 0) {
            return Optional.empty();
        }

        allDataBasedStatisticsDto.setAllStudents(enrolledStudents);

        return Optional.of(allDataBasedStatisticsDto);
    }

}
