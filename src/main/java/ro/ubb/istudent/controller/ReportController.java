package ro.ubb.istudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.ubb.istudent.service.IReportService;
import ro.ubb.istudent.service.ReportService;

import java.util.logging.Logger;

@Controller
public class ReportController {

    IReportService service = new ReportService();

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String home() {
        return "report";
    }
}