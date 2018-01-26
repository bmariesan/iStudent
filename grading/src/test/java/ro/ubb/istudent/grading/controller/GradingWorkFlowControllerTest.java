package ro.ubb.istudent.grading.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.hamcrest.core.Is;
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
import ro.ubb.istudent.grading.criteria.*;
import ro.ubb.istudent.grading.exam.*;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.SolidGrade;
import ro.ubb.istudent.grading.gradingbook.Student;
import ro.ubb.istudent.grading.gradingbook.User;
import ro.ubb.istudent.grading.service.GradingBookService;
import ro.ubb.istudent.grading.service.GradingWorkFlowService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(GradingWorkFlowController.class)
@ContextConfiguration(classes = {GradingWorkFlowController.class})
public class GradingWorkFlowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradingWorkFlowService gradingWorkFlowService;

    @MockBean
    private GradingBookService gradingBookService;

    @Autowired
    private ObjectMapper mapper;

    private GradingCriteria createGradingCriteriaForTest(final Double percent) {
        return new GradingCriteriaFormula(singletonList(
                new GradingCriteriaComponent(GradingCriteriaComponentType.FINAL_EXAM,
                        GradingCriteriaComponentImportance.HIGH, percent)));
    }

    private final Supplier<Question> questionSupplier = () ->
            new ChoiceQuestion("Text", singletonList("good"),
                    asList("not good", "good"), 50.0);

    @Test
    public void whenTeacherGradesAUnitOfWorkForStudent_ExpectValidGrade() throws Exception {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(questionSupplier.get(), singletonList("good")),
                new CompletedExercise(questionSupplier.get(), singletonList("not good")));
        User student = new Student("Student");
        CompletedUnitOfWork unitOfWork =
                new UnitOfWorkWithCompletedExercises(exercises, student);
        Grade expectedGrade = new SolidGrade(0.0, student);
        // when:
        when(gradingWorkFlowService.gradeUnitOfWork(any(CompletedUnitOfWork.class)))
                .thenReturn(expectedGrade);;
        // then:
        mockMvc.perform(get("/work-flow/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unitOfWork)))
                .andDo(print())
                .andExpect(content()
                        .json(mapper.writeValueAsString(expectedGrade)));
    }

    @Test
    public void whenTeacherAddsAUnitOfWorkToStudent_ExpectUnitOfWorkAdded() throws Exception {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(questionSupplier.get(), singletonList("good")),
                new CompletedExercise(questionSupplier.get(), singletonList("not good")));
        User student = new Student("Student");
        CompletedUnitOfWork unitOfWork =
                new UnitOfWorkWithCompletedExercises(exercises, student);
        Course course = new CourseWithGradingCriteria()
                .addUnitOfWork(unitOfWork);
        // when:
        when(gradingWorkFlowService.addUnitOfWorkFromStudent(
                any(CompletedUnitOfWork.class), any(ObjectId.class)))
                .thenReturn(course);
        // then:
        mockMvc.perform(post("/work-flow")
                .param("courseId", course.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unitOfWork)))
                .andDo(print())
                .andExpect(content()
                        .json(mapper.writeValueAsString(course)));
    }

    @Test
    public void whenTeacherGradesCourseForAllStudents_ExpectAllStudentsGraded() throws Exception {
        // given:
        GradingCriteria gradingCriteria = createGradingCriteriaForTest(100.0);
        List<Exercise> exercises = asList(
                new CompletedExercise(questionSupplier.get(), singletonList("good")),
                new CompletedExercise(questionSupplier.get(), singletonList("not good")));
        User student = new Student("Student");
        CompletedUnitOfWork unitOfWork = new UnitOfWorkWithCompletedExercises(
                exercises, student, gradingCriteria.components().get(0));
        Course course = new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(gradingCriteria)
                .addUnitOfWork(unitOfWork);
        List<Grade> expectedGrades = course.workFlows().stream()
                .map(it -> it.grade(gradingCriteria))
                .map(Optional::get)
                .collect(Collectors.toList());
        // when:
        when(gradingWorkFlowService.gradeCourseForEachStudent(any(ObjectId.class)))
                .thenReturn(expectedGrades);
        // then:
        mockMvc.perform(put("/work-flow/all")
                .param("courseId", course.getId().toHexString()))
                .andDo(print())
                .andExpect(jsonPath("$[0].solid-grade.value", Is.is(50.0)));
    }
}