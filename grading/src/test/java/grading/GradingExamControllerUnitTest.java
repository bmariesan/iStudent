package grading;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ro.ubb.istudent.grading.criteria.GradingCriteriaController;
import ro.ubb.istudent.grading.criteria.GradingCriteriaService;
import ro.ubb.istudent.grading.exam.*;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@WebMvcTest(GradingCriteriaController.class)
@ContextConfiguration(classes = {GradingExamController.class})
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
    public void whenGettingRightAnswersFromExam_ExamValid_ExpectRightAnswers() throws Exception {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        Exam exam = new Exam(ObjectId.get(), exercises);
        // when:
        RequestBuilder request = get("/grading-tests/right-answers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(exercises));
        // then:
        mockMvc.perform(request).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenGettingTotalScoreFromExam_ExamValid_ExpectCorrectScore() throws Exception {
        // TODO: Solve Immutable Not Supported By JsonGetter&&Setter
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        Exam exam = new Exam(ObjectId.get(), exercises);
        // when:
        RequestBuilder request = post("/grading-tests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(exam));
        // then:
        //  mockMvc.perform(request).andDo(print())
        //        .andExpect(content().json("10.0"));
    }
}