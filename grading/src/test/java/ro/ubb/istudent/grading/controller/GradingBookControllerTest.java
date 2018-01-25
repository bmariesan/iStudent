package ro.ubb.istudent.grading.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.ubb.istudent.grading.service.GradingBookService;

@RunWith(SpringRunner.class)
@WebMvcTest(GradingBookController.class)
@ContextConfiguration(classes = {GradingBookController.class})
class GradingBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradingBookService service;

    @Autowired
    private ObjectMapper mapper;

    // TODO
}