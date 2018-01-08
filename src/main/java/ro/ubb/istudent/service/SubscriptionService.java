package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.exception.EntityNotFoundException;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

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

    public void subscribeStudentToCourse(String username, CourseDto courseDto) {
        Optional<StudentEntity> studentOptional = studentRepository.findStudentEntityByUsername(username);
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("A student with the username " + username + " was not found!");
        }

        String courseName = courseDto.getName();
        Optional<CourseEntity> courseOptional = courseRepository.findCourseEntityByName(courseName);
        if (!courseOptional.isPresent()) {
            throw new EntityNotFoundException("A course with the name " + courseName + " was not found!");
        }

        StudentEntity student = studentOptional.get();
        CourseEntity course = courseOptional.get();

        List<CourseEntity> registeredCourses = student.getRegisteredCourses();
        registeredCourses.add(course);
        student.setRegisteredCourses(registeredCourses);
        studentRepository.save(student);

        List<StudentEntity> registeredStudents = course.getRegisteredStudents();
        registeredStudents.add(student);
        course.setRegisteredStudents(registeredStudents);
        courseRepository.save(course);
    }
}
