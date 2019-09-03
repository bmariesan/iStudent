package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 08.01.2018.
 */
@Service
public class SubscriptionService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public SubscriptionService(StudentRepository studentRepository,
                               CourseRepository courseRepository,
                               StudentService studentService,
                               CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public StudentDto subscribeStudentToCourse(String username, CourseDto courseDto) {
        StudentEntity student = studentService.getStudentWithUsername(username);
        CourseEntity course = courseService.getCourseWithName(courseDto.getName());

        student.subscribeToCourse(course);
        studentRepository.save(student);

        course.registerStudent(student);
        courseRepository.save(course);

        return StudentDto.createDtoFromEntity(student);
    }

    public List<CourseDto> getAvailableCoursesForStudent(String username) {
        StudentEntity student = studentService.getStudentWithUsername(username);
        List<CourseEntity> courses = courseRepository.findAll();
        return CourseDto.createDtosFromEntities(courses.stream()
                .filter(course -> course.isAvailableForStudent(student) && course.isActive())
                .collect(Collectors.toList()));
    }

    public List<CourseDto> getRegisteredCoursesForStudent(String username) {
        StudentEntity student = studentService.getStudentWithUsername(username);
        List<CourseEntity> courses = student.getRegisteredCourses();
        return CourseDto.createDtosFromEntities(courses);
    }
}
