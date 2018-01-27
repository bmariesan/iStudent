package ro.ubb.istudent.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisplayController { //+Valer

    @GetMapping("/Course-related-analytics")
    public String Course() {
        return "Course_related_analytics";
    }
}
