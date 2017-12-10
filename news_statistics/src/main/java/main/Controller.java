package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    DummyRepo dummyRepo;

    @GetMapping("/")
    public String fuckYou(){
        dummyRepo.save(new DummyClass(1L, "Dummy1"));
        return dummyRepo.findOne(1L).field1;
    }
}
