package ro.ubb.istudent.grading.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseWithGradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.exam.CompletedExercise;
import ro.ubb.istudent.grading.exam.CompletedUnitOfWork;
import ro.ubb.istudent.grading.exam.Exercise;
import ro.ubb.istudent.grading.exam.UnitOfWorkWithCompletedExercises;
import ro.ubb.istudent.grading.exception.GradeNotFound;
import ro.ubb.istudent.grading.gradingbook.*;
import ro.ubb.istudent.grading.service.GradingBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(GradingBookController.class)
@ContextConfiguration(classes = {GradingBookController.class})
public class GradingBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradingBookService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void whenTeacherAddsGradingBookToCourse_ExpectGradingBookAddedToCourse() throws Exception {
        // given:
        GradingBook gradingBook = new SolidGradingBook(new ArrayList<>());
        Course course = new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook);
        // when:
        when(service.saveGradingBookToCourse(any(ObjectId.class), any(GradingBook.class)))
                .thenReturn(course);
        // then:
        mockMvc.perform(post("/grading-book")
                .param("courseId", course.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(gradingBook)))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(course)));
    }

    @Test
    public void whenTeacherDeletesGradingBookFromCourse_ExpectedGradingBookDeletedFromCourse() throws Exception {
        // given:
        GradingBook gradingBook = new SolidGradingBook(new ArrayList<>());
        Course course = new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook);
        // when:
        when(service.deleteGradingBookFromCourse(any(ObjectId.class)))
                .thenReturn(course.replaceGradingBookWith(null));
        // then:
        mockMvc.perform(delete("/grading-book")
                .param("courseId", course.getId().toHexString()))
                .andDo(print())
                .andExpect(jsonPath("gradingBook", IsNull.nullValue()));
    }

    @Test
    public void whenTeacherGetsGradingBookFromCourse_ExpectGradingBook() throws Exception {
        // given:
        GradingBook gradingBook = new SolidGradingBook(new ArrayList<>());
        User teacher = new Teacher("Test");
        Course course = new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook);
        // when:
        when(service.getGradingBookFromCourse(any(ObjectId.class), any(User.class)))
                .thenReturn(gradingBook);
        // then:
        mockMvc.perform(get("/grading-book")
                .param("courseId", course.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(teacher)))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(gradingBook)));
    }

    @Test
    public void whenTeacherAddsGradeToGradingBook_ExpectGradeAddedToCourse() throws Exception {
        // given:
        Grade grade = new SolidGrade(10.0, new Student("Testable"));
        GradingBook gradingBook = new SolidGradingBook(new ArrayList<>());
        Course course = new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook.storeGrade(grade));
        // when:
        when(service.storeGradeInGradingBook(any(Grade.class), any(ObjectId.class)))
                .thenReturn(course);
        // then:
        mockMvc.perform(post("/grading-book/grade")
                .param("courseId", course.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(grade)))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(course)));
    }

    @Test
    public void WhenTeacherDeletesGradeFromGradingBook_ExpectGradeDeletedFromGradingBook() throws Exception {
        // given:
        Grade grade = new SolidGrade(10.0, new Student("Testable"));
        GradingBook gradingBook = new SolidGradingBook(new ArrayList<>());
        Course course = new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook.storeGrade(grade));
        // when:
        when(service.deleteGradeFromGradingBook(any(ObjectId.class), any(ObjectId.class)))
                .thenReturn(course.replaceGradingBookWith(gradingBook));
        // then:
        mockMvc.perform(delete("/grading-book/grade")
                .param("courseId", course.getId().toHexString())
                .param("gradeId", grade.id().toHexString()))
                .andDo(print())
                .andExpect(jsonPath("$.gradingBook.@grading-book.grades",
                        Is.is(emptyList())));
    }
}