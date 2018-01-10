package ro.ubb.istudent.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.ubb.istudent.IStudentApplication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IStudentApplication.class)
@DisplayName("Greeting service integration tests")
class GreetingServiceTest {

    @Autowired
    private GreetingService service;

    @Test
    @DisplayName("Saving a greeting should work")
    void saveGreeting() {
        GreetingDto savedGreeting = service.createGreeting(createGreetingDto("We come in peace!"));
        Assertions.assertAll("Saving greetings should work",
                () -> assertNotNull(savedGreeting, "Greeting creation failed"),
                () -> assertNotNull(savedGreeting.getId(), "Greeting is not saved"),
                () -> assertEquals("We come in peace!", savedGreeting.getMessage(), "Invalid greeting message"));
    }

    @Test
    @DisplayName("Retrieving a greeting by id should work")
    void findGreetingById() {
        // Given we have a greeting already saved
        GreetingDto savedGreeting = service.createGreeting(createGreetingDto("We come in peace!"));
        Assumptions.assumeTrue(savedGreeting != null);
        // When we try to retrieve it
        Optional<GreetingDto> greetingByIdOptional = service.findGreetingById(savedGreeting.getId());
        // Then it should be there
        Assertions.assertAll("Retrieving a greeting by id should work",
                () -> assertTrue(greetingByIdOptional.isPresent(), "Could not find greeting by id"),
                () -> assertEquals(savedGreeting.getId(), greetingByIdOptional.get().getId(), "Retrieved greeting has invalid id"),
                () -> assertEquals(savedGreeting.getMessage(), greetingByIdOptional.get().getMessage(), "Retrieved greeting has different message from the saved one"));

    }

    @Test
    @DisplayName("Removing a greeting by id should work")
    void deleteGreetingById() {
        // Given we have a greeting already saved
        GreetingDto savedGreeting = service.createGreeting(createGreetingDto("We come in peace!"));
        Assumptions.assumeTrue(savedGreeting != null);
        // When we delete it
        service.deleteGreetingById(savedGreeting.getId());
        // Then it should be removed
        Optional<GreetingDto> deletedGreetingOptional = service.findGreetingById(savedGreeting.getId());
        Assertions.assertAll("Removing a greeting by id should work",
                () -> assertFalse(deletedGreetingOptional.isPresent(), "Found greeting by id when it should be deleted"));

    }


    @Test
    @DisplayName("Updating a greeting should work")
    void updateGreeting() {
        // Given we have a greeting already saved
        GreetingDto savedGreeting = service.createGreeting(createGreetingDto("We come in peace!"));
        Assumptions.assumeTrue(savedGreeting != null);
        // When we update it
        savedGreeting.setMessage("Updated message");
        service.updateGreetingWithId(savedGreeting.getId(), savedGreeting);
        // Then it should be updated
        Optional<GreetingDto> updatedGreetingOptional = service.findGreetingById(savedGreeting.getId());
        Assertions.assertAll("Updating a greeting by id should work",
                () -> assertTrue(updatedGreetingOptional.isPresent(), "Could not find greeting by id"),
                () -> assertEquals(savedGreeting.getId(), updatedGreetingOptional.get().getId(), "Updated greeting has invalid id"),
                () -> assertEquals("Updated message", updatedGreetingOptional.get().getMessage(), "Updated greeting has different message from the expected updated one"));

    }


    private GreetingDto createGreetingDto(String message) {
        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setMessage(message);
        return greetingDto;
    }
}
