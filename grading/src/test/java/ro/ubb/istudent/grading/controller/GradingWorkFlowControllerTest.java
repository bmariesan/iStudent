package ro.ubb.istudent.grading.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.ubb.istudent.grading.exam.*;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.SolidGrade;
import ro.ubb.istudent.grading.gradingbook.Student;
import ro.ubb.istudent.grading.gradingbook.User;
import ro.ubb.istudent.grading.service.GradingWorkFlowService;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(GradingWorkFlowController.class)
@ContextConfiguration(classes = {GradingWorkFlowController.class})
public class GradingWorkFlowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradingWorkFlowService service;

    @Autowired
    private ObjectMapper mapper;

    private final Supplier<Question> questionSupplier = () ->
            new ChoiceQuestion("Text", singletonList("good"),
                    asList("not good", "good"), 50.0);

    @Test
    public void whenTeacherGradesAUnitOfWorkForStudent_ExpectValidGrade() throws Exception {
        // TODO: Solve this test!
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(questionSupplier.get(), singletonList("good")),
                new CompletedExercise(questionSupplier.get(), singletonList("not good")));
        User student = new Student("Student");
        CompletedUnitOfWork unitOfWork =
                new UnitOfWorkWithCompletedExercises(exercises, student);
        Grade expectedGrade = new SolidGrade(0.0, student);
        // when:
        when(service.gradeUnitOfWork(any(CompletedUnitOfWork.class)))
                .thenReturn(expectedGrade);

        System.out.println(mapper.writeValueAsString(unitOfWork));
        // then:
        mockMvc.perform(get("/work-flow/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unitOfWork)))
                .andDo(print())
                .andExpect(content()
                        .json(mapper.writeValueAsString(expectedGrade)));
    }
}