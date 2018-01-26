package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.NameEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.exception.EntityNotFoundException;
import ro.ubb.istudent.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public CourseDto createCourse(CourseDto course) {
        return CourseDto.createDtoFromEntity(repository.save(CourseDto.createEntityFromDto(course)));
    }

    public List<CourseDto> findAll() {
        return CourseDto.createDtosFromEntities(repository.findAll());
    }

    CourseEntity getCourseWithName(NameEntity name) {
        Optional<CourseEntity> courseOptional = repository.findCourseEntityByName(name);
        if (!courseOptional.isPresent()) {
            throw new EntityNotFoundException("A course with the name " + name + " was not found!");
        }
        return courseOptional.get();
    }
}
