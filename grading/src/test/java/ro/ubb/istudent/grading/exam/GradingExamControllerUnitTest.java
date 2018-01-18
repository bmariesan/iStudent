package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteria;
import ro.ubb.istudent.grading.exam.controller.GradingExamController;
import ro.ubb.istudent.grading.exam.domain.*;
import ro.ubb.istudent.grading.exam.service.GradingExamService;
import ro.ubb.istudent.grading.gradingbook.domain.*;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@WebMvcTest(GradingExamController.class)
@ContextConfiguration(classes = {GradingExamController.class})
@AutoConfigureMockMvc
public class GradingExamControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradingExamService service;

    @Autowired
    private ObjectMapper mapper;

    private Question makeQuestion() {
        List<String> rightAnswers = singletonList("is good");
        List<String> possibleAnswers = asList("is good", "is not good");
        return new ChoiceQuestion("Text", rightAnswers, possibleAnswers, 10.0);
    }

    @Test
    public void whenGradingExam_ExamValid_ExpectExamGraded() throws Exception {
        // TODO: Solve this test ...
        // given:
        List<CompletedExercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        Exam exam = new ExamWithCompletedExercises(ObjectId.get(), exercises);
        // when:
        RequestBuilder request = get("/exams/score")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(exam));
        // then:
        mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenGradingExam_WithValidExam_ExpectGradeFromServer() throws Exception {
        // given:
        List<CompletedExercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        NormalStudent student = new NormalStudent("Student");
        NormalTeacher teacher = new NormalTeacher("Teacher");
        Exam exam = new ExamWithCompletedExercises(ObjectId.get(), exercises, student, teacher);
        // when:
        when(service.grade(any(CompletedExam.class))).thenReturn(
                new NormalGrade(ObjectId.get(), 10.0, teacher, student));
        RequestBuilder request = post("/exams/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(exam));
        // then:
        mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenGettingRightAnswersFromExam_ExamValid_ExpectRightAnswers() throws Exception {
        // TODO: Solve this test ...
        // given:
        List<CompletedExercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        ExamWithCompletedExercises exam = new ExamWithCompletedExercises(ObjectId.get(), exercises);
        // when:
        RequestBuilder request = get("/grading-tests/right-answers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(exam));
        // then:
        mockMvc.perform(request).andDo(print())
                .andExpect(status().isOk());
    }

}