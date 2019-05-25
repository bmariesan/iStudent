package ro.ubb.istudent.grading.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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
import ro.ubb.istudent.grading.exception.GradingCriteriaNotFound;
import ro.ubb.istudent.grading.service.GradingCriteriaService;

import static java.util.Arrays.asList;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GradingCriteriaController.class)
@ContextConfiguration(classes = {GradingCriteriaController.class})
public class GradingCriteriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradingCriteriaService service;

    @Autowired
    private ObjectMapper mapper;

    private GradingCriteria createGradingCriteriaForTest(final Double percent) {
        return new GradingCriteriaFormula(asList(
                new GradingCriteriaComponent(GradingCriteriaComponentType.FINAL_EXAM,
                        GradingCriteriaComponentImportance.HIGH, percent),
                new GradingCriteriaComponent(GradingCriteriaComponentType.PARTIAL_EXAM,
                        GradingCriteriaComponentImportance.HIGH, percent)));
    }

    @Test
    public void whenInsertingGradingCriteria_CourseExists_ExpectGradingCriteriaSaved() throws Exception {
        // given:
        GradingCriteria gradingCriteria = createGradingCriteriaForTest(50.0);
        Course expectedCourse = new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(gradingCriteria);
        // when:
        when(service.saveGradingCriteriaToCourse(
                Matchers.any(GradingCriteriaFormula.class),
                Matchers.any(ObjectId.class))).thenReturn(expectedCourse);
        // then:
        mockMvc.perform(post("/grading-criteria")
                .param("courseId", expectedCourse.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(gradingCriteria)))
                .andDo(print())
                .andExpect(content()
                        .json(mapper.writeValueAsString(expectedCourse)));
    }

    @Test
    public void whenDeletingGradingCriteria_GradingCriteriaExists_ExpectGradingCriteriaDeleted() throws Exception {
        // given:
        GradingCriteria gradingCriteria = createGradingCriteriaForTest(50.0);
        Course expectedCourse = new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(gradingCriteria);
        // when:
        when(service.deleteGradingCriteriaFromCourse(Matchers.any(ObjectId.class)))
                .thenReturn(expectedCourse.replaceGradingCriteriaWith(null));
        // then:
        mockMvc.perform(delete("/grading-criteria")
                .param("courseId", expectedCourse.getId().toHexString()))
                .andDo(print())
                .andExpect(jsonPath("$.criteria", IsNull.nullValue()));
    }

    @Test
    public void whenUpdatingGradingCriteria_GradingCriteriaExists_ExpectGradingCriteriaDeleted() throws Exception {
        // given:
        GradingCriteria gradingCriteria = createGradingCriteriaForTest(50.0);
        Course expectedCourse = new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(gradingCriteria);
        GradingCriteria updatedGradingCriteria = gradingCriteria.addGradingCriteriaComponent(
                new GradingCriteriaComponent(
                        GradingCriteriaComponentType.FINAL_EXAM,
                        GradingCriteriaComponentImportance.OPTIONAL, 0.0));
        // when:
        when(service.saveGradingCriteriaToCourse(
                Matchers.any(GradingCriteriaFormula.class), Matchers.any(ObjectId.class)))
                .thenReturn(expectedCourse.replaceGradingCriteriaWith(updatedGradingCriteria));
        // then:
        mockMvc.perform(put("/grading-criteria")
                .param("courseId", expectedCourse.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedGradingCriteria)))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(expectedCourse
                        .replaceGradingCriteriaWith(updatedGradingCriteria))));
    }

    @Test
    public void whenInsertingGradingCriteriaWithRedistribution_ExpectCorrectGradingCriteriaInserted() throws Exception {
        // given:
        GradingCriteria gradingCriteria = createGradingCriteriaForTest(20.0);
        Course expectedCourse = new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(new RedistributedGradingCriteria(gradingCriteria));
        // when:
        when(service.saveGradingCriteriaWithRedistribution(
                Matchers.any(GradingCriteria.class), Matchers.any(ObjectId.class)))
                .thenReturn(expectedCourse);
        // then:
        mockMvc.perform(post("/grading-criteria/redistribution")
                .param("courseId", expectedCourse.getId().toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(gradingCriteria)))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(expectedCourse)));
    }

    @Test
    public void whenGettingGradingCriteria_GradingCriteriaNotFound_ExpectGradingCriteriaNotFound() throws Exception {
        // given:
        Course expectedCourse = new CourseWithGradingCriteria();
        // when:
        when(service.deleteGradingCriteriaFromCourse( Matchers.any(ObjectId.class)))
               .thenThrow(new GradingCriteriaNotFound());
        // then:
        mockMvc.perform(delete("/grading-criteria")
                .param("courseId", expectedCourse.getId().toHexString()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}