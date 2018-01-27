package ro.ubb.istudent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.NameEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;
import ro.ubb.istudent.service.SubscriptionService;

import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SubscriptionServiceTests {

    private static final String STUDENT = "studi";
    private static final String COURSE_NAME = "course_name";

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSubscribeToCourse() {
        StudentEntity student = StudentEntity.builder()
                .name(new NameEntity(STUDENT))
                .username(STUDENT)
                .build();
        studentRepository.save(student);
        CourseEntity courseEntity = CourseEntity.builder()
                .name(new NameEntity(COURSE_NAME))
                .active(true)
                .studentLimit(10)
                .build();
        courseRepository.save(courseEntity);
        CourseDto courseDto = CourseDto.createDtoFromEntity(courseEntity);
        subscriptionService.subscribeStudentToCourse(STUDENT,courseDto);

        Optional<CourseEntity> thisCourse = courseRepository.findCourseEntityById(courseEntity.getId().toHexString());
        Assert.assertEquals(STUDENT,thisCourse.get().getRegisteredStudents().get(0).getName().getName());
    }

}
