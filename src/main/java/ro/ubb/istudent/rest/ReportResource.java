package ro.ubb.istudent.rest;

import com.mongodb.util.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.domain.Report;
import ro.ubb.istudent.domain.Statistics;
import ro.ubb.istudent.service.IReportService;
import ro.ubb.istudent.service.ReportService;
import ro.ubb.istudent.util.ResponseUtil;

import java.util.HashMap;

@RequestMapping("/api")
@RestController
public class ReportResource {

    IReportService service =  new ReportService();
    @GetMapping("/report")
    public String getHelloWorldGreeting() {
        Report report = new Report();
        Statistics statistics = new Statistics();
        statistics.setYear(2017);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Design",150);
        hashMap.put("LFTC",21);
        statistics.setResult(hashMap);
        report.getStatistics().add(statistics);
        return report.ToHTML();
    }
}
