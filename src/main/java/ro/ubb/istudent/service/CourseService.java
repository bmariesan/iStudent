package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.repository.CourseRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Optional<CourseDto> findCourseById(String courseId) {
        return repository.findCourseEntityById(courseId)
                .map(this::courseToCourseDTO);
    }

    public CourseDto createCourse(CourseDto course) {
        return courseToCourseDTO(repository.save(courseDTOToEntity(course)));
    }

    public List<CourseDto> findAll() {
        return createFromEntities(repository.findAll());
    }

    private CourseDto courseToCourseDTO(CourseEntity entity) {
        CourseDto courseDto = CourseDto.builder()
                .name(entity.getName())
                .studentLimit(entity.getStudentLimit())
                .build();
        courseDto.setId(entity.getId());

        return courseDto;
    }

    private CourseEntity courseDTOToEntity(CourseDto dto) {
        CourseEntity courseEntity = CourseEntity.builder()
                .name(dto.getName())
                .studentLimit(dto.getStudentLimit())
                .build();
        courseEntity.setId(dto.getId());

        return courseEntity;
    }

    private List<CourseDto> createFromEntities(final Collection<CourseEntity> entities) {
        return entities.stream()
                .map(this::courseToCourseDTO)
                .collect(Collectors.toList());
    }
}
