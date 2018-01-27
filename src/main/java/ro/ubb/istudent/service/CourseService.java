package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.CourseConverter;
import ro.ubb.istudent.converters.strategy2.ConverterStrategy;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.repository.CourseRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private CourseRepository repository;
    private ConverterStrategy converterStrategy;

    public CourseDto save(CourseDto studentDto) {
        return (CourseDto) converterStrategy.createFromEntity(
                repository.save((CourseEntity) converterStrategy.createFromDto(studentDto))
        );
    }

    public Optional<CourseDto> findByName(String name) {
        return repository.findByName(name)
                .map(courseEntity -> (CourseDto)converterStrategy.createFromEntity(courseEntity));
    }
}
