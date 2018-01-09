package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.exception.EntityNotFoundException;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created on 08.01.2018.
 */
@Service
public class SubscriptionService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public SubscriptionService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public StudentDto subscribeStudentToCourse(String username, CourseDto courseDto) {
        StudentEntity student = getStudentWithUsername(username);
        CourseEntity course = getCourseWithName(courseDto.getName());

        student.subscribeToCourse(course);
        studentRepository.save(student);

        course.registerStudent(student);
        courseRepository.save(course);

        return StudentDto.createDtoFromEntity(student);
    }

    public List<CourseDto> getAvailableCoursesForStudent(String username) {
        StudentEntity student = getStudentWithUsername(username);
        List<CourseEntity> courses = courseRepository.findAll();
        return CourseDto.createDtosFromEntities(courses.stream()
                .filter(course -> course.isAvailableForStudent(student))
                .collect(Collectors.toList()));
    }

    private StudentEntity getStudentWithUsername(String username) {
        Optional<StudentEntity> studentOptional = studentRepository.findStudentEntityByUsername(username);
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("A student with the username " + username + " was not found!");
        }
        return studentOptional.get();
    }

    private CourseEntity getCourseWithName(String name) {
        Optional<CourseEntity> courseOptional = courseRepository.findCourseEntityByName(name);
        if (!courseOptional.isPresent()) {
            throw new EntityNotFoundException("A course with the name " + name + " was not found!");
        }
        return courseOptional.get();
    }
}
