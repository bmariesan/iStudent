package ro.ubb.istudent.grading.criteria;

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
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseNotFound;
import ro.ubb.istudent.grading.course.CourseWithGradingCriteria;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alexandru Stoica
 */

@RunWith(SpringRunner.class)
@WebMvcTest(GradingCriteriaController.class)
@ContextConfiguration(classes={GradingCriteriaController.class})
public class GradingCriteriaControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradingCriteriaService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void whenInsertingGradingCriteria_CourseExists_ExpectGradingCriteriaSaved() throws Exception {
        // given:
        GradingCriteria gradingCriteria = new GradingCriteria(
                Collections.singletonList(new GradingCriteriaComponent()));
        String request = mapper.writeValueAsString(gradingCriteria);
        GradingCriteria expectedGradingCriteria =
                new GradingCriteriaWithValidatedPercentage(gradingCriteria);
        Course expectedCourse = new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(expectedGradingCriteria);
        // when:
        when(service.insert(any(GradingCriteria.class), any(ObjectId.class)))
                .thenReturn(expectedCourse);
        // then:
        mockMvc.perform(post("/grading-criteria")
                .param("courseId", expectedCourse.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(content().json(
                        mapper.writeValueAsString(expectedCourse)));
    }

    @Test
    public void whenInsertingGradingCriteria_CourseNotFound_ExpectCourseNotFound() throws Exception {
        // given:
        GradingCriteria gradingCriteria = new GradingCriteria(
                Collections.singletonList(new GradingCriteriaComponent()));
        String request = mapper.writeValueAsString(gradingCriteria);
        Course expectedCourse = new CourseWithGradingCriteria();
        // when:
        when(service.insert(any(GradingCriteria.class), any(ObjectId.class)))
                .thenThrow(CourseNotFound.class);
        // then:
        mockMvc.perform(post("/grading-criteria")
                .param("courseId", expectedCourse.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().is(404));
    }

    @Test
    public void whenGettingGradingCriteria_GradingCriteriaNotFound_ExpectGradingCriteriaNotFound() throws Exception {
        // given:
        GradingCriteria gradingCriteria = new GradingCriteria(
                Collections.singletonList(new GradingCriteriaComponent()));
        String request = mapper.writeValueAsString(gradingCriteria);
        Course expectedCourse = new CourseWithGradingCriteria();
        // when:
        when(service.getByCourseId(any(ObjectId.class)))
                .thenThrow(GradingCriteriaNotFound.class);
        // then:
        mockMvc.perform(get("/grading-criteria/" + expectedCourse.getId().toHexString()))
                .andExpect(status().is(404));
    }



}