import exams.StartApplication;
import exams.service.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StartApplication.class)
@DisplayName("Greeting exams.service integration tests")
class GreetingServiceTest {

    @Autowired
    private Service service;

    @Test
    @DisplayName("Saving a greeting should work")
    void saveGreeting() {
        service.createExams();
        service.createStudents();
    }
}
