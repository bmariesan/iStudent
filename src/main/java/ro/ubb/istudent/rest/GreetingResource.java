package ro.ubb.istudent.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.dto.GreetingDto;
import ro.ubb.istudent.service.GreetingService;
import ro.ubb.istudent.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/api")
@RestController
public class GreetingResource {

    private static final String GREETING_CONTROLLER_MAPPING = "/greeting";
    private static final Logger LOG = LoggerFactory.getLogger(GreetingResource.class);
    private final GreetingService service;
    private final String baseUrl;

    public GreetingResource(GreetingService service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @GetMapping("/greeting/{greetingId}")
    public ResponseEntity getHelloWorldGreeting(@PathVariable("greetingId") String greetingId) {
        System.out.println("what");
        System.out.println(service.findAllGreetings());
        return ResponseUtil.wrapOrNotFound(service.findGreetingById(greetingId));
    }

    @PutMapping("/greeting/{greetingId}")
    public ResponseEntity<Void> updateHelloWorldGreeting(@PathVariable("greetingId") String greetingId, @RequestBody GreetingDto greeting) {
        LOG.debug("Updating greeting with id: " + greetingId + " and new greeting value:" + greeting);
        service.updateGreetingWithId(greetingId, greeting);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(GREETING_CONTROLLER_MAPPING + "/{greetingId}")
    public ResponseEntity<Void> deleteGreeting(@PathVariable("greetingId") String greetingId) {
        LOG.debug("Deleting greeting with id: " + greetingId);
        service.deleteGreetingById(greetingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(GREETING_CONTROLLER_MAPPING)
    public ResponseEntity createGreeting(@RequestBody GreetingDto greeting) throws URISyntaxException {
        LOG.debug("Creating greeting with value: " + greeting);
        GreetingDto savedGreeting = service.createGreeting(greeting);
        return ResponseEntity.created(new URI(baseUrl + GREETING_CONTROLLER_MAPPING + "/" + savedGreeting.getId())).build();
    }

}
