package exams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;
//maybe load the scripts in the app.js?
@Controller
public class StatisticController {
    Logger logger = Logger.getLogger("InfoLogging");
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String home() {
        logger.info("StatisticController");
        return "statistics";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home2() {
        return "statistics";
    }

}